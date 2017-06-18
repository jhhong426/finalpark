package org.zerock.persistence;

import java.util.List;

import org.zerock.domain.LedgerVO;

public interface LedgerDAO {

  public List<LedgerVO> list(Integer boardNum) throws Exception;

  public void create(LedgerVO vo) throws Exception;

  public void update(LedgerVO vo) throws Exception;

  public void delete(Integer num) throws Exception;

  public int getBoardNum(Integer num) throws Exception;
  
  public void updateLedgerCnt(LedgerVO vo)throws Exception;

}
