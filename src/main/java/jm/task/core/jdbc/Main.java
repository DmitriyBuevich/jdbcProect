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
        userService.saveUser("Петр", "Петров", (byte) 31);
        userService.saveUser("Мария", "Сидорова", (byte) 41);
        userService.saveUser("Светлана", "Полякова", (byte) 51);

        System.out.println("-------------------");

        List<User> users = userService.getAllUsers();
        for (User user : users)
            System.out.println(user);


        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
