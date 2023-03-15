package com.lawencon.userservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.core.model.BaseEntity;

@Entity
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(name = "roles_bk", columnNames = { "code" })
})
public class Role extends BaseEntity {
    
	@Column(name = "code", nullable = false)
	private String code;
	
	@Column(name = "name", nullable = false)
	private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
