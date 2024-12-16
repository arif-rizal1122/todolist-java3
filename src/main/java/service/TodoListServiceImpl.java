package service;

import entity.TodoList;
import repository.TodoListRepo;

public class TodoListServiceImpl implements TodolistService {
    public TodoListRepo todoListRepo;

    public TodoListServiceImpl(TodoListRepo todoListRepo) {
        this.todoListRepo = todoListRepo;
    }

    @Override
    public void showTodoList() {
        System.out.println("TODOLIST");
        TodoList[] model = todoListRepo.getAll();

        for (int i = 0; i < model.length; i++) {
            var todo = model[i];
            var nomor =  i + 1;
            if (todo != null){
                System.out.println(nomor + ". " + todo.getTodo());
            }
        }
    }


    @Override
    public void addTodoList(String todo) {
        TodoList todoList = new TodoList(todo);
        todoListRepo.add(todoList);
        System.out.println("SUCCESS MENAMBAHKAN DATA TODOLIST " + todo);
    }

    @Override
    public void removeTodoList(Integer number) {
        boolean success = todoListRepo.remove(number);
        if (success){
            System.out.println("SUCCESS MENGHAPUS TODO : " + number);
        } else {
            System.out.println("GAGAL MENGHAPUS TODO : " + number);
        }
    }

}