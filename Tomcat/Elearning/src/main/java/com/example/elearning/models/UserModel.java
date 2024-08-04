package com.example.elearning.models;

import com.example.elearning.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private List<User> listUsers;

    public UserModel() {
        listUsers = new ArrayList<>();
        listUsers.add(new User(1,"John Doe","john.doe@example.com"));
        listUsers.add(new User(2,"Jane Doe","jane.doe@example.com"));
        listUsers.add(new User(3,"Jack Doe","jack.doe@example.com"));
        listUsers.add(new User(4,"Jack Doe","jack.doe@example.com"));
    }

    public List<User> getListUsers() {
        return listUsers;
    }
}
