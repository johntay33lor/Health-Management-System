package org.health;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManagement {
    private Map<String, User> users;

    public UserManagement() {
        users = new HashMap<>();
    }

    // Create User
    public void createUser(String username) throws UserCreationException {
        if (users.containsKey(username)) {
            throw new UserCreationException("Username already exists. Create a new username.");
        } else {
            User newUser = new User(username);
            users.put(username, newUser);
            System.out.println("User created successfully.");
        }
    }

    // Login as User
    public User loginUser(String username) throws UserLoginException {
        if (users.containsKey(username)) {
            return users.get(username);
        } else {
            throw new UserLoginException("User not found. Please try again.");
        }
    }

}

