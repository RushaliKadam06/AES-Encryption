package com.jsp.person.pan.service;


import com.jsp.person.pan.dao.PersonDao;
import com.jsp.person.pan.dto.Pan;
import com.jsp.person.pan.dto.Person;
import com.jsp.person.pan.util.AES;
import com.jsp.person.pan.util.Constant;

public class PersonService {
	
	PersonDao personDao = new PersonDao();
	
	public void savePerson(Person person) {
	    
		String name=AES.encrypt(person.getName(), Constant.key);
		String email=AES.encrypt(person.getEmail(), Constant.key);
		
		person.setName(name);
		person.setEmail(email);
		
		personDao.savePersone(person);
		
	}
	
	public Person personGetById(int id) {
		Person person = personDao.getPersonById(id);
		String name = AES.decrypt(person.getName(), Constant.key);
		String email = AES.decrypt(person.getEmail(), Constant.key);
		
		person.setName(name);
		person.setEmail(email);
	
		return person;
	}
	
	public void deletePersonById(int id) {
		personDao.deletePersonByID(id);
	}
	
	public void getAllById() {
		personDao.getAllbyId();
	}
	
	public Person updatePersonName(int id,String name) {
		Person person = personDao.getPersonById(id);
  	  String name1=AES.encrypt(name, Constant.key);
  	  person.setName(name1);
  	  return personDao.updateUserById(person);
    }
    
	public Person updatePersonEmail(int id,String email) {
		Person person = personDao.getPersonById(id);
  	  String email1=AES.encrypt(email, Constant.key);
  	  person.setName(email1);
  	  return personDao.updateUserById(person);
    }
    
	

}
