package com.jpa.mainClasses;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jpa.entities.User;

public class TestJPAExample {

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA_test");
		
		EntityManager manager=  entityManagerFactory.createEntityManager();
//		Query query = manager.createNativeQuery("select * from user_tbl where userId=?", User.class).setParameter(1, 1);
		
		Query query = manager.createNativeQuery("select * from user_tbl", User.class);
		
		List<User> user = query.getResultList();
		
		if(user != null){
			System.out.println("userName :" +  user.get(0).getUserName());
			System.out.println("Role :" +  user.get(0).getRole());
		}else{
			System.out.println("No User found");
		}
	}

}
