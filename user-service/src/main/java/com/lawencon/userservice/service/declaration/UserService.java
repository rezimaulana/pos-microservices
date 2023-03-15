package com.lawencon.userservice.service.declaration;

import java.util.Optional;

import com.lawencon.core.dto.response.DataListResDto;
import com.lawencon.core.dto.response.DataResDto;
import com.lawencon.core.dto.response.InsertResDto;
import com.lawencon.core.dto.response.TransactionResDto;
import com.lawencon.userservice.dto.user.UserDataDto;
import com.lawencon.userservice.dto.user.UserInsertReqDto;
import com.lawencon.userservice.model.User;

public interface UserService {
    
    public TransactionResDto<InsertResDto> insert(UserInsertReqDto data);

	public Optional<User> getByEmail(final String email);

	public DataResDto<UserDataDto> getById(String id);

	public DataListResDto<UserDataDto> getAll(Integer page, Integer limit);

	public UserDataDto setToDto(User data);

}
