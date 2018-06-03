package com.agora.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agora.bll.DebateService;
import com.agora.model.Debate;
import com.agora.model.User;


@RestController
public class DebateController {
	
	@Autowired
	private DebateService debateService;
	
	@CrossOrigin(origins = {"http://localhost:3000", "https://agora-admin.herokuapp.com"})
	@RequestMapping("/getDebates")
	public List<Debate> getDebates(){
		return debateService.getAllDebates();
	}
	
	@CrossOrigin(origins = {"http://localhost:3000", "https://agora-admin.herokuapp.com"})
	@RequestMapping(method=RequestMethod.POST, value="/addDebate")
	public List<Debate> addDebate(@RequestBody Debate debate){
		return debateService.addDebate(debate);
	}
	
	@CrossOrigin(origins = {"http://localhost:3000", "https://agora-admin.herokuapp.com"})
	@RequestMapping("/addDebator1/{userId}/{debateId}")
	public void addDebator1(@PathVariable Long userId, @PathVariable Long debateId){
		debateService.addDebator1(userId,debateId);
	}
	
	@CrossOrigin(origins = {"http://localhost:3000", "https://agora-admin.herokuapp.com"})
	@RequestMapping("/addDebator2/{userId}/{debateId}")
	public void addDebator2(@PathVariable Long userId, @PathVariable Long debateId){
		debateService.addDebator2(userId,debateId);
	}
	
	@CrossOrigin(origins = {"http://localhost:3000", "https://agora-admin.herokuapp.com"})
	@RequestMapping("/editDescription/{description}/{debateId}")
	public void editDescription(@PathVariable String description, @PathVariable Long debateId){
		debateService.editDescription(description, debateId);
	}
	
	@CrossOrigin(origins = {"http://localhost:3000", "https://agora-admin.herokuapp.com"})
	@RequestMapping("/editTopic/{topic}/{debateId}")
	public void editTopic(@PathVariable String topic, @PathVariable Long debateId){
		debateService.editTopic(topic, debateId);
	}
	
	
	
}

