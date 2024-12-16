import entity.DatabaseUtil;
import repository.TodoListRepo;
import repository.TodoListRepoImpl;
import service.TodoListServiceImpl;
import service.TodolistService;
import view.TodoListView;

import javax.sql.DataSource;
import java.sql.SQLException;

public class AplikasiTodoListV2 {
    public static void main(String[] args)  {
        DataSource source = DatabaseUtil.getDataSource();
        TodoListRepo todoListRepo = new TodoListRepoImpl(source);

        TodolistService todolistService = new TodoListServiceImpl(todoListRepo);
        TodoListView todoListView = new TodoListView(todolistService);

        todoListView.showTodoList();

    }
}
