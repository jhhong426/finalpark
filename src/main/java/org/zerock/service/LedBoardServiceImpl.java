package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.LedBoardVO;
import org.zerock.domain.LedSearchCriteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.LedBoardDAO;

@Service
public class LedBoardServiceImpl implements LedBoardService {

	 @Inject
	  private LedBoardDAO dao;

	  
	  @Transactional
	  @Override
	  public void regist(LedBoardVO board) throws Exception {
	  
	    dao.create(board);
	    
	    /*
	    String[] files = board.getFiles();
	    
	    if(files == null) { return; } 
	    
	    for (String fileName : files) {
	      dao.addAttach(fileName);
	    }
	    */ 
	  }
	
	  @Transactional(isolation=Isolation.READ_COMMITTED)
	  @Override
	  public LedBoardVO read(Integer num) throws Exception {
	    //dao.updateViewCnt(num);
	    return dao.read(num);
	  }
	  
	  @Transactional
	  @Override
	  public void modify(LedBoardVO board) throws Exception {
	    dao.update(board);
	    /*
	    Integer num = board.getNum();
	    
	    dao.deleteAttach(num);
	    
	    String[] files = board.getFiles();
	    
	    if(files == null) { return; } 
	    
	    for (String fileName : files) {
	      dao.replaceAttach(fileName, bno);
	    }
	    */
	  }

	  @Transactional
	  @Override
	  public void remove(Integer num) throws Exception {
	    //dao.deleteAttach(bno);
	    dao.delete(num);
	  } 

	  @Override
	  public List<LedBoardVO> listAll() throws Exception {
	    return dao.listAll();
	  }

	@Override
	public int checkCreate(String date) throws Exception {
		return dao.checkCreate(date);
		
	}

	@Override
	public LedBoardVO readToday(String date) throws Exception {
		return dao.readToday(date);
	}

	@Override
	public List<LedBoardVO> listSearchCriteria(LedSearchCriteria cri) throws Exception {

	  return dao.listSearch(cri);
	}

	@Override
	public List<String> getAttach(Integer num) throws Exception {
		
		return dao.getAttach(num);
	}

}
