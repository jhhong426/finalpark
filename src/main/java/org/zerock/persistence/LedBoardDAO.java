package org.zerock.persistence;

import java.util.List;

import org.zerock.domain.LedBoardVO;
import org.zerock.domain.LedSearchCriteria;

public interface LedBoardDAO {

	public void create(LedBoardVO vo) throws Exception;

	public LedBoardVO read(Integer num) throws Exception;
	
	public LedBoardVO readToday(String date) throws Exception;

	public void update(LedBoardVO vo) throws Exception;

	public void delete(Integer num) throws Exception;

	public List<LedBoardVO> listAll() throws Exception;
	  
	//use for dynamic sql
	  
	public List<LedBoardVO> listSearch(LedSearchCriteria cri)throws Exception;
	  
	public void updateLedgerCnt(Integer num, int amount)throws Exception;
	
	public int checkCreate(String date);
	
	public void addAttach(String fullName)throws Exception;
	  
	public List<String> getAttach(Integer num)throws Exception;  
	   
	public void deleteAttach(Integer num)throws Exception;
	  
	public void replaceAttach(String fullName, Integer num)throws Exception;
	  
}
