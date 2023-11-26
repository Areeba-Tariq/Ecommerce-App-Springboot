package com.jtspringproject.JtSpringProject.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.sound.midi.Soundbank;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.models.User;


@Repository
public class userDao {
	@Autowired
    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
   @Transactional
    public List<User> getAllUser() {
        Session session = this.sessionFactory.getCurrentSession();
		List<User>  userList = session.createQuery("from CUSTOMER").list();
        return userList;
    }
    
    @Transactional
	public User saveUser(User user) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);
		System.out.println("User added" + user.getId());
        return user;
	}
    //this was comented
//    public User checkLogin() {
//    	this.sessionFactory.getCurrentSession();
//		return null;
//	}
	//This method i have added
//	@Transactional
//	public User checkLogin(String username, String password) {
//		Session session = this.sessionFactory.getCurrentSession();
//		Query<User> query = session.createQuery("from CUSTOMER where username = :username", User.class);
//		query.setParameter("username", username);
//
//		try {
//			User user = query.getSingleResult();
//			if (password.equals(user.getPassword())) {
//				return user;
//			} else {
//				// Incorrect password
//				return null;
//			}
//		} catch (NoResultException e) {
//			// User not found
//			return null;
//		}
//	}
    @Transactional
    public User getUser(String username,String password) {
    	Query query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where username = :username");
    	query.setParameter("username",username);
    	
    	try {
			User user = (User) query.getSingleResult();
			System.out.println(user.getPassword());
			if(password.equals(user.getPassword())) {
				return user;
			}else {		
				return new User();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			User user = new User();
			return user;
		}
    	
    }
}