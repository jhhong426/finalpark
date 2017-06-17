package org.zerock.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.LedBoardVO;
import org.zerock.domain.LedgerVO;


@Repository
public class MainDAOImpl implements MainDAO{
	
	 @Inject
	  private SqlSession session;
	  private static String namespace = "org.zerock.mapper.ledgerMapper";

	 @Override
	  public int count(Integer category) throws Exception {
	    return session.selectOne(namespace + ".count", category);

	  }
	 
	 @Override
	 public int countDate(int category, String date) throws Exception{
		 Map<String, Object> paramMap = new HashMap<>();

		    paramMap.put("category", category);
		    paramMap.put("date", date);
		    
		 return session.selectOne(namespace + ".countDate",paramMap);
	 }
	 
	 public int countMonth(String month) throws Exception{
		 return session.selectOne(namespace + ".countMonth", month);
	 }
	 
	 
	 @Override
	  public List<LedgerVO> allData() throws Exception {
	    return session.selectList(namespace + ".allData");
	  }
}





