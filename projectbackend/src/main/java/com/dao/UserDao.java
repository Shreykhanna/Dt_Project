/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.User;

/**
 *
 * @author shrey
 */
public interface UserDao {
void registration(User user);
boolean isEmailValid(String email);
boolean isUsernameValid(String username);    
User login(User user);
void updateUser(User validUser);
User getUserByUsername(String username);
}
