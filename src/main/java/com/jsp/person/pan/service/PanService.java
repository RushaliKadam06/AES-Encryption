package com.jsp.person.pan.service;

import com.jsp.person.pan.dao.PanDao;
import com.jsp.person.pan.dto.Pan;
import com.jsp.person.pan.dto.Person;
import com.jsp.person.pan.util.AES;
import com.jsp.person.pan.util.Constant;

public class PanService {
	
	PanDao panDao=new PanDao();
	
	public void savePan(Pan pan) {
	   
	   String panNo=AES.encrypt(pan.getPanNo(), Constant.key);
		pan.setPanNo(panNo);
		panDao.savePan(pan);
		
	}
	
	public Pan panGetById(int id) {
		Pan pan = panDao.getPanById(id);
		String panNo = AES.decrypt(pan.getPanNo(), Constant.key);
		
	   pan.setPanNo(panNo);
	   
	
		return pan;
	}
	
	public void deletePannById(int id) {
		panDao.deletePanByID(id);
	}
	
	public void getAllById() {
		panDao.getAllbyId();
	}
	
	public Person getPersonByPanId(int panId) {
		return panDao.getPersonByPanId(panId);
	}
}
