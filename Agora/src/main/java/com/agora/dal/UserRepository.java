package com.agora.dal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.agora.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	
	List<User> findAllByIdNotLike(@Param("id") Long id);
	
	
}

