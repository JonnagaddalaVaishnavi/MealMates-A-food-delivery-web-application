package com.myfoods.dao;

import java.util.List;

import com.myfoods.model.User;

public interface UserDAO {
	void addUser(User user);
	User getUser(int userID);
	void updateUser(User user);
	void deleteUser(int userID);
	List<User>getAllUsers();
	
}
