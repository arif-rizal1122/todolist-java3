package entity;


// membuat table database
public class TodoList {

    private Integer id;
    private String todo;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public TodoList(){}
    public TodoList(String todo){
        this.todo = todo;
    }
    public String getTodo() {
        return todo;
    }
    public void setTodo(String todo) {
        this.todo = todo;
    }


}
