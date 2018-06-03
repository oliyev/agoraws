package com.agora.bll;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.agora.dal.DebateRepository;
import com.agora.model.Debate;



@Service
public class DebateService {

	// Connect to the database
	@Autowired
	private DebateRepository debateRepository;

	// Get all debates from the database
	public List<Debate> getAllDebates() {
		System.out.println("GETTING ALL Debates");
		List<Debate> debates = new ArrayList<>();
		debates = (List<Debate>) ((CrudRepository<Debate, Long>) debateRepository).findAll();
		
		return debates;
	}
	
	
	public List<Debate> addDebate(Debate debate) {
		System.out.println("Adding debate");
		debate.setTimestamp(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		Debate saved = ((CrudRepository<Debate, Long>) debateRepository).save(debate);

		List<Debate> debates = new ArrayList<>();
		debates = (List<Debate>) ((CrudRepository<Debate, Long>) debateRepository).findAll();
		
		return debates;
		
	}
	
	public void addDebator1(Long userId, Long debateId) {
		Debate debate = debateRepository.findById(debateId).orElse(null);
		debate.setDebator1Id(userId);
		((CrudRepository<Debate, Long>) debateRepository).save(debate);
	}
	
	public void addDebator2(Long userId, Long debateId) {
		Debate debate = debateRepository.findById(debateId).orElse(null);
		debate.setDebator2Id(userId);
		((CrudRepository<Debate, Long>) debateRepository).save(debate);
	}
	
	public void editDescription(String description, Long debateId) {
		Debate debate = debateRepository.findById(debateId).orElse(null);
		debate.setDescription(description);
		((CrudRepository<Debate, Long>) debateRepository).save(debate);
	}
	
	public void editTopic(String topic, Long debateId) {
		Debate debate = debateRepository.findById(debateId).orElse(null);
		debate.setDescription(topic);
		((CrudRepository<Debate, Long>) debateRepository).save(debate);
	}
	
	public List<Debate> deleteDebate(Long debateId) {
		((CrudRepository<Debate, Long>) debateRepository).deleteById(debateId);
		
		List<Debate> debates = new ArrayList<>();
		debates = (List<Debate>) ((CrudRepository<Debate, Long>) debateRepository).findAll();
		
		return debates;
	}


}
