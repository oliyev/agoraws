package com.agora.bll;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.agora.dal.UserLocationRepository;
import com.agora.dal.UserRepository;
import com.agora.model.Following;
import com.agora.model.LoginHolder;
import com.agora.model.Avatar;
import com.agora.model.Debate;
import com.agora.model.User;
import com.agora.model.UserLocation;

@Service
public class UserService {

	// Connect to the database
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserLocationRepository userLocationRepository;

	// Get all users from the database - WORKS
	public List<User> getAllUsers() {
		System.out.println("GETTING ALL USERS");
		List<User> users = new ArrayList<>();
		users = (List<User>) ((CrudRepository<User, Long>) userRepository).findAll();

		return users;
	}

	// Add user - WORKS
	public String addUser(User user) {
		List<User> users = new ArrayList<>();
		users = (List<User>) ((CrudRepository<User, Long>) userRepository).findAll();
		System.out.println("List Size: " + users.size());
		
		for (User agoraUser : users) {
			System.out.println("Users List: " + agoraUser.getUsername() + "}");
			System.out.println("Given User: " + user.getUsername() + "}");
			if (agoraUser.getUsername().toUpperCase().equals(user.getUsername().toUpperCase())) {
				System.out.println("DUPLICATE");
				return "exists";
			}
		}
		
		user.setUsername(user.getUsername().toLowerCase());
		User savedUser = ((CrudRepository<User, Long>) userRepository).save(user);
		return "" + savedUser.getId();
	}

	// Get one user and return it
	public User getUser(LoginHolder loginHolder) {
		List<User> users = new ArrayList<>();
		users = (List<User>) ((CrudRepository<User, Long>) userRepository).findAll();

		for (User agoraUser : users) {
			System.out.println("Username: " + agoraUser.getUsername() + " Password: " + agoraUser.getPassword());
			if (agoraUser.getPassword().equals(loginHolder.getPassword())
					&& agoraUser.getUsername().equals(loginHolder.getUsername())) {
				System.out.println("FOUND A MATCH!!!");
				return agoraUser;
			}
		}
		return null;
	}

	public List<User> getAllNotFollowing(Long id) {
		List<User> users = new ArrayList<>();

		// get all users that are not the user asking
		users = userRepository.findAllByIdNotLike(id);
		// get the calling user
		User user = userRepository.findById(id).orElse(null);
		// get existing friends
		Set<Following> friends = user.getFriends();
		
		Iterator<User> iter = users.iterator();

		while (iter.hasNext()) {
		    User u = iter.next();

		    for (Following f : friends) {
				if (u.getId() == f.getFriendId()) {
					iter.remove();
				}
			}
		}

		return users;
	}

	public void addFriendship(Long requesterId, Long friendId) {
		User requester = userRepository.findById(requesterId).orElse(null);

		requester = userRepository.findById(requesterId).orElse(null);
		
		Following following = new Following();
		following.setFollower(requester);
		following.setFriendId(friendId);

		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		requester.getFriends().add(following);
		
		// ((CrudRepository<Friendship, Long>) friendshipRepository).save(fs);
		((CrudRepository<User, Long>) userRepository).save(requester);
	}

	
	public void removeFriendship(Long requesterId, Long friendId) {
		System.out.println("REMOVING USER");
		User requester = userRepository.findById(requesterId).orElse(null);
		System.out.println("REQUESTER:: " + requester.getFirstname() + " " + requester.getId());

		requester = userRepository.findById(requesterId).orElse(null);
		
		Set<Following> following = requester.getFriends();
		for (Following fl : following) {
			System.out.println("FRIEND:: " + fl.getFriendId());
			System.out.println("VS:: " + friendId);
		}
		
		Iterator<Following> iter = following.iterator();
		while (iter.hasNext()) {
		    Following f = iter.next();
		    System.out.println("Following f::: " + f.getFriendId());
		    if (f.getFriendId() == friendId) {
		    	System.out.println("MATCH::: REMOVING FRIENDSHIP ::::");
		    	iter.remove();
		    }
		}
		
		for (Following following2 : following) {
			System.out.println("AFTER REMOVAL:" + following2.getFriendId());
		}

		requester.setFriends(following);
		
		// ((CrudRepository<Friendship, Long>) friendshipRepository).save(fs);
		((CrudRepository<User, Long>) userRepository).save(requester);
		
	}

	public List<User> getAllFriends(Long id) {
		List<User> friends = new ArrayList<User>();
		User requester = userRepository.findById(id).orElse(null);

		Set<Following> rawList = requester.getFriends();
		
		for (Following following : rawList) {
			System.out.println("Friend ID: " + following.getFriendId());
			friends.add(userRepository.findById(following.getFriendId()).orElse(null));
		}

		return friends;
	}

	public void addUserLocation(UserLocation userLocation) {
		if (userLocation == null)
			return;

		User user = userRepository.findById(userLocation.getUser().getId()).orElse(null);
		user.getUserLocations().add(userLocation);
		userLocation.setUser(user);

		((CrudRepository<User, Long>) userRepository).save(user);
	}

	public Set<UserLocation> getLastUserLocation(Long userId, int amount) {
		Set<UserLocation> locationSet = userRepository.findById(userId).orElse(null).getUserLocations();
		Object[] locations = locationSet.toArray();
		locationSet.removeAll(locationSet);

		int count = amount > locations.length ? locations.length : amount;
		
		for (int i = 0; i < count; i++) {
			locationSet.add((UserLocation)locations[i]);
			System.out.println(locations[i].toString());
			System.out.println("locations type " + ((UserLocation)locations[i]).getClass());
		}
		System.out.println("locations count " + locations.length);
		return locationSet;
	}
	
	public List<UserLocation> getFriendsLocation(Long userId){
		List<UserLocation> friendsLocation = new ArrayList<>();
		Set<Following> followers = userRepository.findById(userId).orElse(null).getFriends();
		
		for (Following follower : followers) {
			UserLocation[] locations = (UserLocation[]) follower.getFollower().getUserLocations().toArray();
			
			if (locations.length > 0) 
				friendsLocation.add((UserLocation) locations[0]);
		}
		
		return friendsLocation;
	}
	
	public UserLocation getFriendLocation(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		Set<UserLocation> locs = user.getUserLocations();
		
		for (UserLocation userLocation : locs) {
			System.out.println(userLocation.getLatitude());
		}
		Object[] locations = locs.toArray();
		
		if (locations != null && locations.length > 0) {
			Object lastKnownLocation = locations[0];
			UserLocation location = (UserLocation) lastKnownLocation;
			return location;
		}
		else
			return null;
	}
	
	public void deleteAllUserLocation(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		((CrudRepository<UserLocation, Long>) userLocationRepository).deleteAll(user.getUserLocations());
	}
	
	public void addProfilePic(Avatar photo) {
		User user = userRepository.findById(photo.getId()).orElse(null);
		user.setPhoto(photo.getPhoto());
		
		((CrudRepository<User, Long>) userRepository).save(user);
	}
	
	public String getPhoto(Long id) {
		User user = userRepository.findById(id).orElse(null);
		
		return user.getPhoto();
	}
	
	public User getUser(Long id) {
		User user = userRepository.findById(id).orElse(null);
		
		return user;
	}
	
	public void editUser(User user) {
		User temp = userRepository.findById(user.getId()).orElse(null);
		temp.setFirstname(user.getFirstname());
		temp.setLastname(user.getLastname());
		temp.setEmail(user.getEmail());
		((CrudRepository<User, Long>) userRepository).save(temp);
	}

}
