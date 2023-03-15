package com.lawencon.userservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.core.model.BaseEntity;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "users1_bk", columnNames = { "username" }),
        @UniqueConstraint(name = "users2_bk", columnNames = { "email" })
})
public class User extends BaseEntity {
    
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "fullname", nullable = false)
	private String fullname;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
