package com.agora.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agora.bll.*;
import com.agora.model.LoginHolder;
import com.agora.model.Avatar;
import com.agora.model.User;
import com.agora.model.UserLocation;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUsers")
	public List<User> getUsers(){
		return userService.getAllUsers();
	}
	
	@RequestMapping("/getNotFollowing/{id}")
	public List<User> getFollowing(@PathVariable Long id){
		return userService.getAllNotFollowing(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/register")
	public String registerUser(@RequestBody User user){
		return userService.addUser(user);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/login")
	public User login(@RequestBody LoginHolder loginHolder) {
		return userService.getUser(loginHolder);
	}
	
	@RequestMapping("/addFriendship/{idReq}/{idFriend}")
	public void addFriendship(@PathVariable Long idReq, @PathVariable Long idFriend){
		userService.addFriendship(idReq,idFriend);
	}
	
	@RequestMapping("/removeFriendship/{idReq}/{idFriend}")
	public void removeFriendship(@PathVariable Long idReq, @PathVariable Long idFriend){
		userService.removeFriendship(idReq,idFriend);
	}
	
	@RequestMapping("/getFriends/{id}")
	public List<User> getFriends(@PathVariable Long id){
		return userService.getAllFriends(id);
	}
	
	@RequestMapping("/addUserLocation")
	public void addUserLocation(@RequestBody UserLocation userLocation) {
		userService.addUserLocation(userLocation);
	}
	
	@RequestMapping("/getLastUserLocation/{userId}/{count}")
	public Set<UserLocation> getLastUserLocation(@PathVariable Long userId, @PathVariable int count){
		return userService.getLastUserLocation(userId, count);
	}
	
	@RequestMapping("/getFriendsLocation/{userId}")
	public List<UserLocation> getFriendsLocation(@PathVariable Long userId){
		return userService.getFriendsLocation(userId);
	}
	
	@RequestMapping("/getFriendLocation/{userId}")
	public UserLocation getFriendLocation(@PathVariable Long userId){
		return userService.getFriendLocation(userId);
	}
	
	@RequestMapping("/deleteAllUserLocation/{userId}")
	public void deleteAllUserLocation(@PathVariable long userId) {
		userService.deleteAllUserLocation(userId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addProfilePic")
	public void addProfilePic(@RequestBody Avatar photo) {
		userService.addProfilePic(photo);
	}
	
	@RequestMapping("/getPhoto/{id}")
	public String getPhoto(@PathVariable Long id){
		return userService.getPhoto(id);
	}
	
	@RequestMapping("/getUser/{id}")
	public User getUser(@PathVariable Long id){
		return userService.getUser(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/edit")
	public void editUser(@RequestBody User user){
		userService.editUser(user);
	}
	
}
