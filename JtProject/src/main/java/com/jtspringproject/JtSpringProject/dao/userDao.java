package com.jtspringproject.JtSpringProject.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.sound.midi.Soundbank;

import com.jtspringproject.JtSpringProject.models.Product;
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
		this.sessionFactory.getCurrentSession().save(user);
		System.out.println("User added" + user.getId());
		return user;
	}

	//    public User checkLogin() {
//    	this.sessionFactory.getCurrentSession().
//    }
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
	@Transactional
	public User getUserById(int id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where id = :id");
		query.setParameter("id",id);

		try {
			User user = (User) query.getSingleResult();
			System.out.println(user.getUsername());
			if(id==user.getId()) {
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
	//start of code addition
	@Transactional
	public Boolean deletUser(int id) {

		Session session = this.sessionFactory.getCurrentSession();
		//Object persistanceInstance = session.load(User.class, id);
		User user = session.find(User.class, id);

		if (user != null) {
			session.delete(user);
			System.out.println(user.getPassword());
			return true;
		}
		return false;
	}
	//end of code addition
}
