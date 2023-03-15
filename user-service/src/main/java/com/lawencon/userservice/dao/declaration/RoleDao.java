package com.lawencon.userservice.dao.declaration;

import java.util.Optional;

import com.lawencon.userservice.model.Role;

public interface RoleDao {

    public Optional<Role> getById(String id);

}
