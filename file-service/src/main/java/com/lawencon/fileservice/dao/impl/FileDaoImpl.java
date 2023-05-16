package com.lawencon.fileservice.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.core.dao.impl.BaseDaoImpl;
import com.lawencon.fileservice.dao.declaration.FileDao;
import com.lawencon.fileservice.model.File;

@Repository
public class FileDaoImpl extends BaseDaoImpl implements FileDao{

    @Override
    public File insert(File data) {
        this.em.persist(data);
        return data;
    }

    @Override
    public Optional<File> getById(String id) {
        final File findOne = this.em.find(File.class, id);
        final Optional<File> result = Optional.ofNullable(findOne);
        if(result.isPresent()) {
            em.detach(findOne);
        }
        return result;
    }

	@Override
	public boolean deleteById(String id) {
		final String sql = " DELETE FROM File WHERE id = :id ";
		final int result = this.em.createQuery(sql)
				.setParameter("id", id)
				.executeUpdate();
		return result > 0;
	}
    
}
