package com.example.jakartalab2.dao;

import com.example.jakartalab2.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final List<User> store = new ArrayList<>();

    public User getById(int id){
        return store.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public User getUserByLoginPassword(final String login, final String password){
        return store
            .stream()
            .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
            .findAny()
            .orElse(null);
    }

    public boolean addUser(final User user){
        boolean isExist = store.stream().anyMatch(u -> u.getLogin().equals(user.getLogin()));

        if (isExist) return false;

        return store.add(user);
    }

    public User.ROLE getRoleByLoginPassword(final String login, final String password){
        User user = store
            .stream()
            .filter(u -> u.getLogin().equals(login) && u.getPassword().equals(password))
            .findAny()
            .orElse(null);

        if (user == null) return User.ROLE.UNKNOWN;
        return user.getRole();
    }

    public boolean isUserExist(final String login, final String password) {
        return store.stream().anyMatch(u -> u.getLogin().equals(login) && u.getPassword().equals(password));
    }
}
