package com.nramiscal.auth.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(min=3, message="Username must be greater than 3 characters")
	private String username;
	
	@Column
	@Size(min=5, message="Password must be greater than 5 characters")
	private String password;
	
	@Transient
	private String passwordConfirmation;
	
	@Column
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date created_at;
	
	@Column
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date updated_at;
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    
    // controllers
    
    public User() {
    	
    }
			
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	// created_at, updated_at

	@PrePersist
	protected void onCreate(){
	this.created_at = new Date();
	}

	@PreUpdate
	protected void onUpdate(){
	this.updated_at = new Date();
	}
	
	// getters

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public Date getUpdated_at() {
		return updated_at;
	}
	
	// setters

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	
}
