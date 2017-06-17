package org.zerock.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.LedBoardVO;
import org.zerock.domain.LedSearchCriteria;

@Repository
public class LedBoardDAOImpl implements LedBoardDAO {
	
	  @Inject
	  private SqlSession session;

	  private static String namespace = "org.zerock.mapper.ledBoardMapper";

	  @Override
	  public void create(LedBoardVO vo) throws Exception {
	    session.insert(namespace + ".create", vo);
	  }

	  @Override
	  public LedBoardVO read(Integer num) throws Exception {
	    return session.selectOne(namespace + ".read", num);
	  }

	  @Override
	  public void update(LedBoardVO vo) throws Exception {
	    session.update(namespace + ".update", vo);
	  }

	  @Override
	  public void delete(Integer num) throws Exception {
	    session.delete(namespace + ".delete", num);
	  }

	  @Override
	  public List<LedBoardVO> listAll() throws Exception {
	    return session.selectList(namespace + ".listAll");
	  }

	  @Override
	  public List<LedBoardVO> listSearch(LedSearchCriteria cri) throws Exception {

	    return session.selectList(namespace + ".listSearch", cri);
	  }

	  @Override
	  public void updateLedgerCnt(Integer num, int amount) throws Exception {

	    Map<String, Object> paramMap = new HashMap<String, Object>();

	    paramMap.put("num", num);
	    paramMap.put("amount", amount);

	    session.update(namespace + ".updateLedgerCnt", paramMap);
	  }

	@Override
	public int checkCreate(String date) {
		return session.selectOne(namespace + ".checkCreate", date);
		
	}

	@Override
	public LedBoardVO readToday(String date) throws Exception {
		return session.selectOne(namespace + ".readToday", date);
	}

	@Override
	public void addAttach(String fullName) throws Exception {
	    
	  session.insert(namespace+".addAttach", fullName);
	    
	}
	  
	@Override
	public List<String> getAttach(Integer num) throws Exception {
	    
	  return session.selectList(namespace +".getAttach", num);
	  }
	 

	@Override
	public void deleteAttach(Integer num) throws Exception {

	  session.delete(namespace+".deleteAttach", num);
	    
	}

	@Override
	public void replaceAttach(String fullName, Integer num) throws Exception {
	    
	  Map<String, Object> paramMap = new HashMap<String, Object>();
	    
	  paramMap.put("num", num);
	  paramMap.put("fullName", fullName);
	    
	  session.insert(namespace+".replaceAttach", paramMap);
	    
	}

}
