package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.models.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtspringproject.JtSpringProject.dao.userDao;
import com.jtspringproject.JtSpringProject.models.User;

@Service
public class userService {
	@Autowired
	private userDao userDao;

	public List<User> getUsers(){
		return this.userDao.getAllUser();
	}
	public User getUserById(int id){
		return this.userDao.getUserById(id);
	}

	public User addUser(User user) {
		return this.userDao.saveUser(user);
	}

	public User checkLogin(String username,String password) {
		return this.userDao.getUser(username, password);
	}

	//start of code addition
	public boolean deleteUser(int id) {
		return this.userDao.deletUser(id);
	}
	//end of code addition

}
