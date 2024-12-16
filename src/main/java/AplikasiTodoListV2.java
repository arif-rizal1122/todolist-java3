import repository.TodoListRepo;
import repository.TodoListRepoImpl;
import service.TodoListServiceImpl;
import service.TodolistService;
import view.TodoListView;

public class AplikasiTodoListV2 {
    public static void main(String[] args) {

        TodoListRepo todoListRepo = new TodoListRepoImpl();
        TodolistService todolistService = new TodoListServiceImpl(todoListRepo);
        TodoListView todoListView = new TodoListView(todolistService);

        todoListView.showTodoList();

    }
}
