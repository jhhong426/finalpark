package org.zerock.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.LedgerVO;
import org.zerock.service.LedgerService;
import org.zerock.domain.PageMaker;

@RestController
@RequestMapping("/replies")
public class LedgerController {

  @Inject
  private LedgerService service;

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<String> register(@RequestBody LedgerVO vo) {

    ResponseEntity<String> entity = null;
    try {
      service.addLedger(vo);
      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @RequestMapping(value = "/{boardNum}", method = RequestMethod.GET)
  public ResponseEntity<List<LedgerVO>> list(@PathVariable("boardNum") Integer boardNum) {

    ResponseEntity<List<LedgerVO>> entity = null;
    try {
      entity = new ResponseEntity<>(service.listLedger(boardNum), HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return entity;
  }

  @RequestMapping(value = "/{num}", method = { RequestMethod.PUT, RequestMethod.PATCH })
  public ResponseEntity<String> update(@PathVariable("num") Integer num, @RequestBody LedgerVO vo) {

    ResponseEntity<String> entity = null;
    try {
      vo.setNum(num);
      service.modifyLedger(vo);

      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @RequestMapping(value = "/{num}", method = RequestMethod.DELETE)
  public ResponseEntity<String> remove(@PathVariable("num") Integer num) {

    ResponseEntity<String> entity = null;
    try {
      service.removeLedger(num);
      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

}
