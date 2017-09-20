package com.nramiscal.auth.services;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nramiscal.auth.repos.RoleRepo;
import com.nramiscal.auth.repos.UserRepo;
import com.nramiscal.auth.models.User;


@Service
public class UserService {

	private UserRepo userRepo;
	private RoleRepo roleRepo;
	private BCryptPasswordEncoder bCPE;
	
	public UserService(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder bCPE) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.bCPE = bCPE;
	}
	
	// 1
    public void saveWithUserRole(User user) {
        user.setPassword(bCPE.encode(user.getPassword()));
        user.setRoles(roleRepo.findByName("ROLE_USER"));
        userRepo.save(user);
    }
     
     // 2 
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCPE.encode(user.getPassword()));
        user.setRoles(roleRepo.findByName("ROLE_ADMIN"));
        userRepo.save(user);
    }    
    
    // 3
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
        
    }
}
