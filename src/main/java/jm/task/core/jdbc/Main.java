package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Иван", "Иванов", (byte) 21);
        String name1 = userService.getAllUsers().get(0).getName();
        System.out.println("User с именем – " + name1 + " добавлен в базу данных");

        userService.saveUser("Петр", "Петров", (byte) 31);
        String name2 = userService.getAllUsers().get(1).getName();
        System.out.println("User с именем – " + name2 + " добавлен в базу данных");

        userService.saveUser("Мария", "Сидорова", (byte) 41);
        String name3 = userService.getAllUsers().get(2).getName();
        System.out.println("User с именем – " + name3 + " добавлен в базу данных");

        userService.saveUser("Светлана", "Полякова", (byte) 51);
        String name4 = userService.getAllUsers().get(3).getName();
        System.out.println("User с именем – " + name4 + " добавлен в базу данных");

        System.out.println("-------------------");



        List<User> users = userService.getAllUsers();
        for (User user : users)
            System.out.println(user);


        userService.cleanUsersTable();


        userService.dropUsersTable();

    }
}
