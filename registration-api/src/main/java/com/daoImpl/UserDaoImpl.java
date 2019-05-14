package com.daoImpl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Repository;

import com.customExceptions.MovieAppException;
import com.dao.UserDao;
import com.models.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	/*@Autowired
	SessionFactory sessionFactory;*/
	
	@Autowired
	EntityManager entityManager;

	@Override
	public String registerUser(User user) throws MovieAppException {
		
		
		/*Query query = entityManager.createNativeQuery("select userId, dob, emailAddress, firstName, lastName, password, userName from movieApp.user", User.class);		
		query.getResultList().stream().forEach((o)-> {
			User u = (User) o;
			System.out.println(u);
		});*/
		
		/*List<User> userlist = query.getResultList();
		userlist.forEach((o)-> {
			User u = (User) o;
			System.out.println(u);
		});*/
		//System.out.println(user1);
		
		//Query query = entityManager.createQuery("insert into user values " + user);
		entityManager.persist(user);
		
		
		//Session session = sessionFactory.getCurrentSession();
		//Query query = session.createQuery("insert into user values " + user);
		//int result = query.executeUpdate();
		
		//session.getTransaction().begin();
		
		//session.save(user);
		
		//session.getTransaction().commit();
		
		/*if(result != 1){
			
			throw new MovieAppException("Not able to register user. Please try again!!!!");
		}*/
		
		return "User registered Successfully!!!";
	}

	@Override
	public List<User> getUsers() {
		Query query = entityManager.createNativeQuery("select * from user", User.class);
		return query.getResultList();
	}

	@Override
	public User getUser(long userId) {
		Query query = entityManager.createNativeQuery("select * from user where user_id = " + userId, User.class);
		return (User) query.getSingleResult();
	}

	@Override
	public User updateUser(User user) {
		//User updateUser = entityManager.merge(user);
		// return updateUser;
		
		
		
		StringBuffer sqlQuery = new StringBuffer("update user set")
				.append(" dob = '").append(new Date (user.getDob().getTime())).append("'")
				.append(", email_address = '").append(user.getEmailAddress()).append("'")
				.append(", first_name = '").append(user.getFirstName()).append("'")
				.append(", last_name = '").append(user.getLastName()).append("'")
				.append(", password = '").append(user.getPassword()).append("'")
				.append(" where user_name = '").append(user.getUserName()).append("'");
				
		
 		Query query = entityManager.createNativeQuery(sqlQuery.toString(), User.class);
		int result = query.executeUpdate();
		
		if(result == 1){
			return user;
		}
		
		return null;
	}

	@Override
	public String deleteUser(long userId) {
		User user = entityManager.find(User.class, userId); 
		entityManager.remove(user);
		return "User with id="+ userId + " is removed.";
	}

}
