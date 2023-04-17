package com.jsp.person.pan.controller;

import com.jsp.person.pan.dto.Pan;
import com.jsp.person.pan.dto.Person;
import com.jsp.person.pan.service.PanService;
import com.jsp.person.pan.service.PersonService;

public class TestPerson {

	public static void main(String[] args) {

		PersonService personService = new PersonService();
		PanService panService = new PanService();
		Person person = panService.getPersonByPanId(1);
	    System.out.println(person.getId());


	}

}
