/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author shrey
 * 
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses(){
		// TODO Auto-generated method stub
		return new Class[]{WebConfig.class,DbConfig.class};
	}
         
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{WebConfig.class,DbConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	} 
    

    
}
