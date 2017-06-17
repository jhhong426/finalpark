package org.zerock.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.domain.LedBoardVO;
import org.zerock.domain.LedgerVO;
import org.zerock.persistence.MainDAO;

@Service
public class MainServiceImpl implements MainService {
	
	@Inject MainDAO dao;
	
	@Override
	  public int count(Integer category) throws Exception {
	    return dao.count(category);
	  }
	
	@Override
	 public int countDate(int category, String date) throws Exception{
		return dao.countDate(category,date);
	}
	
	@Override
	 public int countMonth(String month) throws Exception{
		return dao.countMonth(month);
	}
	
	
	@Override
	public List<LedgerVO> allData() throws Exception {
		return dao.allData();
	}
	
}
