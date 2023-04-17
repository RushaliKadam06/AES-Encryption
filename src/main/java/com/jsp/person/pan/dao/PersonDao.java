package com.jsp.person.pan.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jsp.person.pan.dto.Pan;
import com.jsp.person.pan.dto.Person;
import com.jsp.person.pan.util.AES;
import com.jsp.person.pan.util.Constant;

public class PersonDao {
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("rushali");
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();
	
	public Person savePersone(Person person) {
		
		if(person!=null) {
			entityTransaction.begin();
			entityManager.persist(person);
			entityTransaction.commit();
		}
		
		return person;
	}
	
	public Person getPersonById(int id) {
		if(id>0) {
		Person person = entityManager.find(Person.class, id);
		return person;
		}else {
			return null;
		}
	}
	
	public void deletePersonByID(int id) {
		Person person = entityManager.find(Person.class, id);
		entityTransaction.begin();
		entityManager.remove(person);
		entityTransaction.commit();
	}
	
	public Person updateUserById(Person person) {
		entityTransaction.begin();
		entityManager.merge(person);
		entityTransaction.commit();
		
		return person;
	}
	
	public void getAllbyId() {
	     
		String sql="select s from Person s";
	    Query query =	entityManager.createQuery(sql);
        List<Person> persons = query.getResultList();
        
    	  
        for(Person p : persons) {
          
          String name=AES.decrypt(p.getName(), Constant.key);
	      String email=AES.decrypt(p.getEmail(), Constant.key);
	      
	      p.setName(name);
	      p.setEmail(email);
	      
     	  System.out.println(p.getId());
     	  System.out.println(p.getName());
     	  System.out.println(p.getEmail());
     	  
        }

}
	
	
}
