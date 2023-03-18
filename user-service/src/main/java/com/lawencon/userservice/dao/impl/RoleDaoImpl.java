package com.lawencon.userservice.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.core.dao.impl.BaseDaoImpl;
import com.lawencon.userservice.dao.declaration.RoleDao;
import com.lawencon.userservice.model.Role;

@Repository
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {
    
    @Override
    public Optional<Role> getById(String id) {
        final Role findOne = this.em.find(Role.class, id);
        final Optional<Role> result = Optional.ofNullable(findOne);
        if(result.isPresent()) {
            em.detach(findOne);
        }
        return result;
    }

}
