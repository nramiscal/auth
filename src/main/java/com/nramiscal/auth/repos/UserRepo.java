package com.nramiscal.auth.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nramiscal.auth.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

}
