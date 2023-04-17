package com.jsp.person.pan.controller;

import com.jsp.person.pan.dto.Pan;
import com.jsp.person.pan.dto.Person;
import com.jsp.person.pan.service.PanService;
import com.jsp.person.pan.service.PersonService;

public class TestPan {
	public static void main(String[] args) {
		
		Person person = new Person();
		person.setName("Rushikesh");
		person.setEmail("Rushikesh@gmail.com");
		
		Pan pan = new Pan();
		pan.setPanNo("def1234");
		pan.setPerson(person);
		
		PanService panService=new PanService();
		PersonService personService=new PersonService();
		personService.savePerson(person);
		panService.savePan(pan);
		
		
	}

}
