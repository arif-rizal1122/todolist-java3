package arif.rizal.data.repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.DatabaseUtil;
import entity.TodoList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.TodoListRepoImpl;

import javax.sql.DataSource;

public class RepositoryTest {
    private HikariDataSource source;
    private TodoListRepoImpl todoListRepo;

    @BeforeEach
    void setUp(){
        source = DatabaseUtil.getDataSource();
        todoListRepo = new TodoListRepoImpl(source);

    }

    @Test
    void tetAdd(){
        TodoList todoList = new TodoList();
        todoList.setTodo("arif rizal");

        todoListRepo.add(todoList);



    }

    @AfterEach
    void tearDown(){
       source.close();
    }
}
