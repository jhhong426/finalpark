package org.zerock.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.util.MediaUtils;
import org.zerock.util.UploadFileUtils;

@Controller
public class LedUploadController {

  private static final Logger logger = LoggerFactory.getLogger(LedUploadController.class);

  @Resource(name = "uploadPath")
  String uploadPath;

  @RequestMapping(value = "/user/uploadForm", method = RequestMethod.GET)
  public void uploadForm() {
  }

  @RequestMapping(value ="/user/uploadForm", method=RequestMethod.POST, 
          produces = "text/plain;charset=UTF-8")
public String uploadForm(MultipartFile file)throws Exception{

logger.info("originalName: " + file.getOriginalFilename());

new ResponseEntity<>(
        UploadFileUtils.uploadFile(uploadPath, 
              file.getOriginalFilename(), 
              file.getBytes()), 
        HttpStatus.CREATED);
return "redirect:/ledger/bbs";
}

  @RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
  public void uploadAjax() {
  }

  private String uploadFile(String originalName, byte[] fileData) throws Exception {

    UUID uid = UUID.randomUUID();

    String savedName = uid.toString() + "_" + originalName;

    File target = new File(uploadPath, savedName);

    FileCopyUtils.copy(fileData, target);

    return savedName;

  }
  
  @ResponseBody
  @RequestMapping(value ="/uploadAjax", method=RequestMethod.POST, 
                  produces = "text/plain;charset=UTF-8")
  public  ModelAndView uploadAjax(MultipartFile file, ModelAndView mav)throws Exception{
    
    logger.info("originalName: " + file.getOriginalFilename());
    String savedName = file.getOriginalFilename();
   
    mav.setViewName("ledger/input");
    mav.addObject("savedName", savedName);
  
    return mav;
  }
  
  
  @ResponseBody
  @RequestMapping("/displayFile")
  public ResponseEntity<byte[]>  displayFile(String fileName)throws Exception{
    
    InputStream in = null; 
    ResponseEntity<byte[]> entity = null;
    
    logger.info("FILE NAME: " + fileName);
    
    try{
      
      String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
      
      MediaType mType = MediaUtils.getMediaType(formatName);
      
      HttpHeaders headers = new HttpHeaders();
      
      in = new FileInputStream(uploadPath+fileName);
      
      if(mType != null){
        headers.setContentType(mType);
      }else{
        
        fileName = fileName.substring(fileName.indexOf("_")+1);       
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Content-Disposition", "attachment; filename=\""+ 
          new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
      }

        entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
          headers, 
          HttpStatus.CREATED);
    }catch(Exception e){
      e.printStackTrace();
      entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
    }finally{
      in.close();
    }
      return entity;    
  }
    
  @ResponseBody
  @RequestMapping(value="/deleteFile", method=RequestMethod.POST)
  public ResponseEntity<String> deleteFile(String fileName){
    
    logger.info("delete file: "+ fileName);
    
    String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
    
    MediaType mType = MediaUtils.getMediaType(formatName);
    
    if(mType != null){      
      
      String front = fileName.substring(0,12);
      String end = fileName.substring(14);
      new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
    }
    
    new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
    
    
    return new ResponseEntity<String>("deleted", HttpStatus.OK);
  }  
  
  @ResponseBody
  @RequestMapping(value="/deleteAllFiles", method=RequestMethod.POST)
  public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[] files){
    
    logger.info("delete all files: "+ Arrays.toString(files));
    
    if(files == null || files.length == 0) {
      return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }

    return new ResponseEntity<String>("deleted", HttpStatus.OK);
  }  

}