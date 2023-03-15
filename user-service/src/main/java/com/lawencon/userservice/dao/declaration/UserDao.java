package com.lawencon.userservice.dao.declaration;

import java.util.List;
import java.util.Optional;

import com.lawencon.userservice.model.User;

public interface UserDao {
    public User insert(User data);

    public Optional<User> getById(String id);

    public List<User> getAll(Integer page, Integer limit);

    public Optional<User> getByEmail(final String email);
}