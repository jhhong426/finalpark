package org.zerock.persistence;

import java.util.Date;
import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.LedgerVO;

public interface MainDAO {
	public int count(Integer category) throws Exception;
	
	 public int countDate(int category, String date) throws Exception;
	 public int countMonth(String month) throws Exception;
	 
	 public List<LedgerVO> allData() throws Exception ;
}
