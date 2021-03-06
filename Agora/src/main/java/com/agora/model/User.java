package com.agora.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity //mark this class as an entity
public class User implements Serializable{
	
	@Id @GeneratedValue
	private long id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String photo;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Following> friends = new HashSet<Following>();
	

	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<UserLocation> userLocations = new HashSet<>();
	
	/*@JsonManagedReference
	@OneToMany(mappedBy = "debater", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Debate> debateHistory = new HashSet<>();*/
	
	
	public User(long id, String username, String password, String firstname, String lastname,
			String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
	public User() {} //default constructor

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Set<Following> getFriends() {
		return friends;
	}

	public void setFriends(Set<Following> friends) {
		this.friends = friends;
	}

	public Set<UserLocation> getUserLocations() {
		return userLocations;
	}

	public void setUserLocations(Set<UserLocation> userLocations) {
		this.userLocations = userLocations;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/*public Set<Debate> getDebateHistory() {
		return debateHistory;
	}

	public void setDebateHistory(Set<Debate> debateHistory) {
		this.debateHistory = debateHistory;
	}*/
	
}
