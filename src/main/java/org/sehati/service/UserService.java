package org.sehati.service;

import org.sehati.domain.User;

public interface UserService {

	public User getUser(String username, String password);
	public void addUser(User user);
	
}
