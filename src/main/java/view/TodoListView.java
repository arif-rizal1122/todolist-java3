package view;

import service.TodolistService;
import util.InputUtil;

public class TodoListView {
    private TodolistService todolistService;

    public TodoListView(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    public void showTodoList(){
        while (true){
            todolistService.showTodoList();

            System.out.println("MENU : ");
            System.out.println("1. tambah : ");
            System.out.println("2. hapus : ");
            System.out.println("x. keluar : ");

            String input = InputUtil.input("pilih");
            if (input.equals("1")){
                addTodoList();
            } else if (input.equals("2")){
                removeTodoList();
            } else if (input.equals("x")) {
                break;
            } else {
                System.out.println("pilihan tidak dikenali!!!!!!!!");
            }
        }
    }

    public void addTodoList(){
        System.out.println("menambhakan todolist ");
        String todo = InputUtil.input("todo (x jika batal)");

        if (todo.equals("x")){
            // batal
        } else {
            todolistService.addTodoList(todo);
        }
    }
    public void removeTodoList(){
        System.out.println("menghapus todolist ");

        String number = InputUtil.input("menghapus todolist (x jika batal) ");
        if (number.equals("x")){
            // batal
        } else {
            todolistService.removeTodoList(Integer.valueOf(number));
        }
    }

}
