package com.lawencon.fileservice.dao.declaration;

import java.util.Optional;

import com.lawencon.fileservice.model.File;

public interface FileDao {
    
    public File insert(File data);

    public Optional<File> getById(String id);

    public boolean deleteById(String id);

}
