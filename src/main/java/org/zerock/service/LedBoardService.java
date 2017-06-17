package org.zerock.service;

import java.util.List;

import org.zerock.domain.LedBoardVO;
import org.zerock.domain.LedSearchCriteria;

public interface LedBoardService {

	  public void regist(LedBoardVO board) throws Exception;

	  public LedBoardVO read(Integer num) throws Exception;
	  
	  public LedBoardVO readToday(String date) throws Exception;

	  public void modify(LedBoardVO board) throws Exception;

	  public void remove(Integer num) throws Exception;

	  public List<LedBoardVO> listAll() throws Exception;
	  
	  public int checkCreate(String date) throws Exception;
	  
	  public List<LedBoardVO> listSearchCriteria(LedSearchCriteria cri) throws Exception;
	  
	  public List<String> getAttach(Integer num)throws Exception;
}
