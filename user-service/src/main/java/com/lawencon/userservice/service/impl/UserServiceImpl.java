package com.lawencon.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.core.constant.ResponseConst;
import com.lawencon.core.dao.impl.BaseDaoImpl;
import com.lawencon.core.dto.response.DataListResDto;
import com.lawencon.core.dto.response.DataResDto;
import com.lawencon.core.dto.response.InsertResDto;
import com.lawencon.core.dto.response.TransactionResDto;
import com.lawencon.core.util.AuthenticationUtil;
import com.lawencon.userservice.dao.declaration.RoleDao;
import com.lawencon.userservice.dao.declaration.UserDao;
import com.lawencon.userservice.dto.user.UserDataDto;
import com.lawencon.userservice.dto.user.UserInsertReqDto;
import com.lawencon.userservice.model.Role;
import com.lawencon.userservice.model.User;
import com.lawencon.userservice.service.declaration.UserService;

@Service
public class UserServiceImpl extends BaseDaoImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private AuthenticationUtil authenticationUtil;
	
	@Autowired
	private PasswordEncoder passwordEncode;

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optional = userDao.getByEmail(email);
		if(optional.isPresent()) {
			return new org.springframework.security.core
					.userdetails.User(email, optional.get().getPassword(), new ArrayList<>());
		}
		throw new UsernameNotFoundException("Wrong Email or Password!");
	}

	public Optional<User> getByEmail(final String email) {
		return userDao.getByEmail(email);
	}

    @Transactional(rollbackOn = Exception.class)
    @Override
    public TransactionResDto<InsertResDto> insert(UserInsertReqDto data) {
        final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
		try {
			final User user = new User();
            user.setUsername(data.getUsername());
            user.setFullname(data.getFullname());
            user.setEmail(data.getEmail());
			final String hash = passwordEncode.encode(data.getPassword());
            user.setPassword(hash);
			final Optional<Role> role = roleDao.getById(data.getRole());
            user.setRole(role.get());
			user.setCreatedBy(authenticationUtil.getPrincipal().getId());
			final User insertOne = userDao.insert(user);
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
    public DataResDto<UserDataDto> getById(String id) {
        final Optional<User> optional = userDao.getById(id);
		User findOne = null;
		if (optional.isPresent()) {
			findOne = optional.get();
			final UserDataDto responseDb = setToDto(findOne);
			final DataResDto<UserDataDto> responseBe = new DataResDto<UserDataDto>();
			responseBe.setData(responseDb);
			return responseBe;
		} else {
			throw new RuntimeException("Not found!");
		}
    }

    @Override
    public DataListResDto<UserDataDto> getAll(Integer page, Integer limit) {
        final List<UserDataDto> responseDb = new ArrayList<>();
		final List<User> find = userDao.getAll(page, limit);
		for (int i = 0; i < find.size(); i++) {
			final User user = find.get(i);
			final UserDataDto result = setToDto(user);
			responseDb.add(result);
		}
		final DataListResDto<UserDataDto> responseBe = new DataListResDto<UserDataDto>();
		responseBe.setData(responseDb);
		return responseBe;
    }

	@Override
	public UserDataDto setToDto(User data) {
		final UserDataDto dto = new UserDataDto();
		dto.setId(data.getId());
        dto.setUsername(data.getUsername());
        dto.setFullname(data.getFullname());
        dto.setEmail(data.getEmail());
		dto.setVer(data.getVer());
		dto.setIsActive(data.getIsActive());
		return dto;
	}
    
}
