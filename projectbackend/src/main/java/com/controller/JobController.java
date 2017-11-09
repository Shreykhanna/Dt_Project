/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.JobDao;
import com.dao.UserDao;
import com.model.Job;
import com.model.Error;
import com.model.User;
import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author shrey
 */
@Controller
public class JobController{
	
    @Autowired
    private JobDao jobDao;
    
    @Autowired
    private UserDao userDao;
    
    @RequestMapping(value="/savejob" ,method=RequestMethod.POST)
    public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session){
    	System.out.println("USernamae inside jobcontroller"+session.getAttribute("username"));
    	 if(session.getAttribute("username")==null){    	 
         Error error=new Error(5, "Unauthorized user");
         return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
     }  
     System.out.println("one");
     String username=(String)session.getAttribute("username");
     System.out.println("two");
     User user=userDao.getUserByUsername(username);
     System.out.println("three");
     if(user.getRole().equals("ADMIN")){
         try{
             System.out.println("isndie if");
             job.setPostedOn(new Date());
             jobDao.saveJob(job);
             return new ResponseEntity<Job>(job,HttpStatus.OK);
         }catch(Exception e){
             Error error=new Error(7, "Unable to insert job details");
             return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }else{
    	 System.out.println("Above access denied");
      Error error=new Error(6, "Access denied");
      return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
     }
    
}
}
