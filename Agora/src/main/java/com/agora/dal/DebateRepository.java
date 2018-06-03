package com.agora.dal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.agora.model.Debate;

public interface DebateRepository extends CrudRepository<Debate, Long> {
	

	
	
}
