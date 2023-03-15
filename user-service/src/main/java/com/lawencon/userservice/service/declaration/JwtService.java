package com.lawencon.userservice.service.declaration;

import java.util.Map;

public interface JwtService {
    
    public String generateJwt(Map<String, Object> claims);

}
