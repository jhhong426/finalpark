package org.zerock.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.ReplyVO;
import org.zerock.service.MainService;
import org.zerock.service.ReplyService;

@RestController
@RequestMapping("/date")
public class dateController {
	private static final Logger logger = LoggerFactory.getLogger(dateController.class);
  @Inject
  private MainService service;

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public ResponseEntity<Map> date(@RequestBody String tdata) {
	 
	  System.out.printf("test!!!");
	  System.out.printf(tdata);
	  String date = tdata.substring(0,10);
	  System.out.printf(date);
	  
	 
	
	  
    ResponseEntity<Map> entity = null;
  
    
    try {
    	Map<String,Integer> map = new HashMap();
    	map.put("category1", service.countDate(1, date));
    	map.put("category2", service.countDate(2, date));
    	map.put("category3", service.countDate(3, date));
    	map.put("category4", service.countDate(4, date));
    	map.put("category5", service.countDate(5, date));
    	map.put("category6", service.countDate(6, date));
    	map.put("my Test", 1234);
    	
    	Integer a = service.countDate(6, date);
    	String test = a.toString();
    	logger.info(test);
    	

      entity = new ResponseEntity<Map>(map, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<Map>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }
}