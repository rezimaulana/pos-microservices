package com.lawencon.fileservice.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.core.constant.ResponseConst;
import com.lawencon.core.dao.impl.BaseDaoImpl;
import com.lawencon.core.dto.file.FileDataDto;
import com.lawencon.core.dto.file.FileInsertReqDto;
import com.lawencon.core.dto.response.DataResDto;
import com.lawencon.core.dto.response.DeleteResDto;
import com.lawencon.core.dto.response.InsertResDto;
import com.lawencon.core.dto.response.TransactionResDto;
import com.lawencon.core.util.AuthenticationUtil;
import com.lawencon.fileservice.dao.declaration.FileDao;
import com.lawencon.fileservice.model.File;
import com.lawencon.fileservice.service.declaration.FileService;

@Service
public class FileServiceImpl extends BaseDaoImpl implements FileService {

    @Autowired
    private FileDao fileDao;

	@Autowired
	private AuthenticationUtil authenticationUtil;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public TransactionResDto<InsertResDto> insert(FileInsertReqDto data) {
        final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
		try {
			final File file = new File();
			file.setEncode(data.getEncode());
			file.setExtension(data.getExtension());
            file.setCreatedBy(authenticationUtil.getPrincipal().getId());
			final File insertOne = fileDao.insert(file);
			final InsertResDto responseDb = new InsertResDto();
			responseDb.setId(insertOne.getId());
			responseBe.setData(responseDb);
			responseBe.setMessage(ResponseConst.CREATED.getResponse());
		} catch (Exception e) {
			responseBe.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseBe;
    }

    @Override
    public DataResDto<FileDataDto> getById(String id) {
        final Optional<File> optional = fileDao.getById(id);
		File findOne = null;
		if (optional.isPresent()) {
			findOne = optional.get();
			final FileDataDto responseDb = setToDto(findOne);
			final DataResDto<FileDataDto> responseBe = new DataResDto<FileDataDto>();
			responseBe.setData(responseDb);
			return responseBe;
		} else {
			throw new RuntimeException("Not found!");
		}
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public TransactionResDto<DeleteResDto> deleteById(String id) {
		final TransactionResDto<DeleteResDto> responseBe = new TransactionResDto<DeleteResDto>();
        try {
            final boolean deleteOne = fileDao.deleteById(id);
            if(deleteOne) {
				final DeleteResDto responseDb = new DeleteResDto();
				responseDb.setId(id);
				responseBe.setData(responseDb);
				responseBe.setMessage(ResponseConst.DELETED.getResponse());                	
            } else {
				throw new RuntimeException("Not Found!");
            }
        } catch (Exception e) {
            responseBe.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseBe;
    }
    
	@Override
	public FileDataDto setToDto(File data) {
		final FileDataDto dto = new FileDataDto();
		dto.setId(data.getId());
		dto.setEncode(data.getEncode());
        dto.setExtension(data.getExtension());
		dto.setVer(data.getVer());
		dto.setIsActive(data.getIsActive());
		return dto;
	}
    
}
