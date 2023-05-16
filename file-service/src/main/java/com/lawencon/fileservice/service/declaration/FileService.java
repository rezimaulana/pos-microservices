package com.lawencon.fileservice.service.declaration;

import com.lawencon.core.dto.file.FileDataDto;
import com.lawencon.core.dto.file.FileInsertReqDto;
import com.lawencon.core.dto.response.DataResDto;
import com.lawencon.core.dto.response.DeleteResDto;
import com.lawencon.core.dto.response.InsertResDto;
import com.lawencon.core.dto.response.TransactionResDto;
import com.lawencon.fileservice.model.File;

public interface FileService {
    
    public TransactionResDto<InsertResDto> insert(FileInsertReqDto data);

	public DataResDto<FileDataDto> getById(String id);
	
	public TransactionResDto<DeleteResDto> deleteById(String id);

	public FileDataDto setToDto(File data);

}
