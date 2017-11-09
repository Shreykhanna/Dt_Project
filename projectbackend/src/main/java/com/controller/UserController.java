/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.UserDao;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import com.model.Error;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author shrey
 */
@Controller
public class UserController {
    public UserController()
    {
    	System.out.println("Inside userConroller consturctor");
    }
    @Autowired
   private UserDao userDao;
   @RequestMapping(value="/registration",method=RequestMethod.POST)
  public ResponseEntity<?> registration(@RequestBody User user){
	   System.out.println("inside usercontroller");
       if(!userDao.isEmailValid(user.getEmail())){
    	   
          Error error=new Error(2,"Duplicate email address");
          return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
          
       }
       if(!userDao.isUsernameValid(user.getUsername())){
          Error error=new Error(3, "Username already exists..please enter different user name");
          return new ResponseEntity<Error>(error, HttpStatus.IM_USED);
       }
       try {
    	   System.out.println("inside try catch");

           userDao.registration(user);
           return new ResponseEntity<User>(user,HttpStatus.OK);
       }catch(Exception e){
           Error error=new Error(1,"Unable to register .."+e.getMessage());
           return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
       }
  }
  @RequestMapping(value = "/login",method=RequestMethod.POST)
  public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
	  System.out.println("Username inside login of usercontroller"+user.getUsername());
  User validUser=userDao.login(user);
  if(validUser==null){
      Error error=new Error(4,"Invalid username/password");
      return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
  }
      validUser.setOnline(true);
      userDao.updateUser(validUser);
      session.setAttribute("username", validUser.getUsername());
      return new ResponseEntity<User>(user, HttpStatus.OK);
  }
  @RequestMapping(value="/updateprofile",method=RequestMethod.PUT)
  public ResponseEntity<?> updateUser(@RequestBody User user,HttpSession session){
	 
	  String username=(String)session.getAttribute("username");
	  User validUser=userDao.login(user);
	  if(session.getAttribute("username")==null){
	      Error error=new Error(5,"Unautarized Access");
	      return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	  }
	  try{
		  user.setUsername(username);
		  userDao.updateUser(user);
		  return new ResponseEntity<User>(user,HttpStatus.OK);
		  
		  
	  }catch(Exception ex)
	  {
		  Error error=new Error(6,"Unable to update");
		  return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	    
	  }
  @RequestMapping(value="/logout",method=RequestMethod.PUT)
  public ResponseEntity<?> logout(HttpSession session)
  {
      String username=(String)session.getAttribute("username");
      System.out.println("helloo");
      System.out.println("username"+username);
      if(username==null){
          Error error=new Error(5,"Please login...");
          return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
      }
      User user=userDao.getUserByUsername(username);
      user.setOnline(false);
      userDao.updateUser(user);
      session.removeAttribute("username");
      session.invalidate();
      return new ResponseEntity<User>(user,HttpStatus.OK);
  }
}
