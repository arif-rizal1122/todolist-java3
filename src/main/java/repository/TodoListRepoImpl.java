package repository;

import entity.TodoList;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoListRepoImpl implements TodoListRepo{
    private final DataSource dataSource;
    public TodoListRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public TodoList[] getAll() {
       String sql = "SELECT id, todo FROM todolist";
       try(Connection connection = dataSource.getConnection();
           Statement statement = connection.createStatement();
           ResultSet set = statement.executeQuery(sql)) {

           List<TodoList> list = new ArrayList<>();
           while (set.next()){
           TodoList todoList = new TodoList();
           todoList.setId(set.getInt("id"));
           todoList.setTodo(set.getString("todo"));
           list.add(todoList);
           }
           return list.toArray(new TodoList[]{});
       } catch (SQLException e){
          throw new RuntimeException(e);
       }
    }

    @Override
    public void add(TodoList todoList) {
        String sql = "INSERT INTO todolist(todo) VALUES  (?)";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, todoList.getTodo());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isExists(Integer number){
        String sql = "SELECT id FROM todolist WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, number);
        try(ResultSet set = statement.executeQuery()) {
            if (set.next()){
                return true;
            } else {
                return false;
            }
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean remove(Integer number) {
        if (isExists(number)){
            String sql = "DELETE FROM todolist WHERE id = ?";
            try(Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, number);
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return false;
        }
    }

}


























