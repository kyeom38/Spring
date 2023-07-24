package net.softsociety.test3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.softsociety.test3.dao.AddInfoDAO;
import net.softsociety.test3.domain.AddInfo;

@Service
public class AddInfoServiceImple implements AddInfoService {

	@Autowired
	AddInfoDAO dao;
	@Override
	public int insertAddInfo(AddInfo a) 
	{
		int n = dao.addinfo(a);
		return n;
	}

	@Override
	public int deleteAddInfo(int num) {
		// TODO Auto-generated method stub
		return 0;
	}

}
