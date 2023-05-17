package com.lawencon.userservice.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.core.dao.impl.BaseDaoImpl;
import com.lawencon.userservice.dao.declaration.UserDao;
import com.lawencon.userservice.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    
    @Override
    public User insert(User data) {
        this.em.persist(data);
        return data;
    }

    @Override
    public Optional<User> getById(String id) {
        final User findOne = this.em.find(User.class, id);
        final Optional<User> result = Optional.ofNullable(findOne);
        if(result.isPresent()) {
            em.detach(findOne);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAll(Integer page, Integer limit) {
        final String sql = "SELECT * FROM users doc ";
        final Query query = this.em.createNativeQuery(sql, User.class);
        query.setFirstResult((page-1) * limit);
        query.setMaxResults(limit);
        final List<User> result = query.getResultList();
        return result;
    }

    public Optional<User> getByEmail(final String email)  {		
		final String sql = " SELECT u "
				+ "FROM User u "
				+ "INNER JOIN FETCH u.role r "
				+ "WHERE u.email = :email";
		final User findOne = this.em.createQuery(sql, User.class).setParameter("email", email).getSingleResult();
		final Optional<User> result = Optional.ofNullable(findOne);
		return result;
	}

    @Override
    public Integer countAll(){
        final String sql = "SELECT COUNT(*) FROM users doc ";
        final Query query = this.em.createNativeQuery(sql);
        BigInteger countData = (BigInteger) query.getSingleResult();
		Integer count = countData.intValue();
		return count;
    }

}
