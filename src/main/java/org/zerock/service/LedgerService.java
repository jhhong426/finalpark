package org.zerock.service;

import java.util.List;

import org.zerock.domain.LedgerVO;

public interface LedgerService {

  public void addLedger(LedgerVO vo) throws Exception;

  public List<LedgerVO> listLedger(Integer boardNum) throws Exception;

  public void modifyLedger(LedgerVO vo) throws Exception;

  public void removeLedger(Integer num) throws Exception;

}
