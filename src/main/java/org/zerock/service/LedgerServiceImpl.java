package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.LedgerVO;
import org.zerock.persistence.LedBoardDAO;
import org.zerock.persistence.LedgerDAO;

@Service
public class LedgerServiceImpl implements LedgerService {

  @Inject
  private LedgerDAO ledgerDAO;
  
  @Inject
  private LedBoardDAO ledBoardDAO;
  

  @Transactional
  @Override
  public void addLedger(LedgerVO vo) throws Exception {

	//ledgerDAO.updateLedgerCnt(vo.getBoardNum());
	ledgerDAO.updateLedgerCnt(vo);
    ledgerDAO.create(vo);
    
  }
  
  @Transactional
  @Override
  public void removeLedger(Integer num) throws Exception {

    int boardNum = ledgerDAO.getBoardNum(num);
    ledgerDAO.delete(num);
    //ledgerDAO.updateLedgerCnt(vo);
  }   

  @Override
  public List<LedgerVO> listLedger(Integer boardNum) throws Exception {

    return ledgerDAO.list(boardNum);
  }

  @Override
  public void modifyLedger(LedgerVO vo) throws Exception {

    ledgerDAO.update(vo);
  }

}
