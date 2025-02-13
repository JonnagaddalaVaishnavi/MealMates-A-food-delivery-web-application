package com.myfoods.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.myfoods.model.User;

public interface UserDAO {
	void addUser(User user);
	User getUser(int userID);
	User getUserByEmail(String email);
	void updateUser(User user);
	void deleteUser(int userID);
	List<User>getAllUsers();
	void updateLastLoginDate(int userId, LocalDateTime now);
}
