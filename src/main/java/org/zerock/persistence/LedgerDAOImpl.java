package org.zerock.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.LedgerVO;


@Repository
public class LedgerDAOImpl implements LedgerDAO {

  @Inject
  private SqlSession session;

  private static String namespace = "org.zerock.mapper.ledgerMapper";

  @Override
  public List<LedgerVO> list(Integer boardNum) throws Exception {

    return session.selectList(namespace + ".list", boardNum);
  }

  @Override
  public void create(LedgerVO vo) throws Exception {

    session.insert(namespace + ".create", vo);
  }

  @Override
  public void update(LedgerVO vo) throws Exception {

    session.update(namespace + ".update", vo);
  }

  @Override
  public void delete(Integer rno) throws Exception {

    session.update(namespace + ".delete", rno);
  }

  @Override
  public int getBoardNum(Integer num) throws Exception {

    return session.selectOne(namespace + ".getBoardNum", num);
  }
  
  
}
