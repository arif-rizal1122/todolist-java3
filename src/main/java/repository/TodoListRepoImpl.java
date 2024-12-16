package repository;

import entity.TodoList;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        String sql = "INSERT INTO todolist(todo) VALUES = (?)";
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

    @Override
    public boolean remove(Integer number) {
        // Validasi input
        if (number <= 0 || number > data.length) {
            return false;
        }

        // Periksa apakah slot yang akan dihapus kosong
        if (data[number - 1] == null) {
            return false;
        }

        // Geser elemen-elemen setelah yang dihapus
        for (int i = (number - 1); i < data.length - 1; i++) {
            data[i] = data[i + 1];
        }

        // Set elemen terakhir menjadi null
        data[data.length - 1] = null;

        return true;
    }

}
