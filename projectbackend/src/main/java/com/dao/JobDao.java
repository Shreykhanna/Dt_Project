/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Job;
import java.util.List;

/**
 *
 * @author shrey
 */
public interface JobDao
{
void saveJob(Job job);  
List<Job> getAlljobs();
Job getJobById(int id);
}
