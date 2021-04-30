package com.example.bootstrap.dao;


import com.example.bootstrap.model.Role;
import com.example.bootstrap.model.User;

import java.util.List;

public interface UserDao {
    public List<User> listUser();

    public void addUser(User user);

    public void editUser(User user);

    public void removeUser(Long id);

    public User getUserById(Long id);

    public User getUserByName(String name);

    public Role getRoleByName(String name);

    public User getUserByEmail(String email);

}
