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

public class PanDao {
	
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("rushali");
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();
	
	
	public Pan savePan(Pan pan) {
	
		if(pan!=null) {
			entityTransaction.begin();
			entityManager.persist(pan);
		    entityTransaction.commit();
		}
		
		return pan;
	}
	
	public Pan getPanById(int id) {
		if(id>0) {
		Pan pan = entityManager.find(Pan.class, id);
		return pan;
		}else {
			return null;
		}
	}
	
	public void deletePanByID(int id) {
		Pan pan = entityManager.find(Pan.class, id);
		entityTransaction.begin();
		entityManager.remove(pan);
		entityTransaction.commit();
	}
	
	public Pan updateUserById(Pan pan) {
		entityTransaction.begin();
		entityManager.merge(pan);
		entityTransaction.commit();
		
		return pan;
	}
	
	public void getAllbyId() {
	     
		String sql="select s from Pan s";
	    Query query =	entityManager.createQuery(sql);
        List<Pan> pans = query.getResultList();
        
    	  
        for(Pan p : pans) {
          
          String panNo=AES.decrypt(p.getPanNo(), Constant.key);
	      	      
	      p.setPanNo(panNo);
	      
     	  System.out.println(p.getId());
     	  System.out.println(p.getPanNo());
     	  System.out.println(p.getPerson());
        }

}
	
       public Person  getPersonByPanId(int panId) {
    	PanDao panDao = new PanDao();
		Pan pan = panDao.getPanById(panId);
   		int personId=pan.getPerson().getId();
   		PersonDao personDao=new PersonDao();
   		Person person = personDao.getPersonById(personId);
   		return person;
       }

}
