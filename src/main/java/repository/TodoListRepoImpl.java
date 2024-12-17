package repository;

import entity.TodoList;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoListRepoImpl implements TodoListRepo{
    public TodoList[] data = new TodoList[4];
    private final DataSource dataSource;
    public TodoListRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public TodoList[] getAll() {
        return data;
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

    public void resizeIsFull(){
        // jika penuh, kita resize ukuran array 2x lipat
        if (isFull()){
            var temp = data; // array lama
            data = new TodoList[data.length * 2]; // array baru
            for (int i = 0; i < temp.length; i++) {
               data[i] = temp[i];
            }
        }
    }
    public boolean isFull(){
        // cek apakah model penuh
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null){
                // model masih ada yg kosong
                return false;
            }
        }
        return true;
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


























