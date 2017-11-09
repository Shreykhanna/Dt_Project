/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.config;

import com.model.User;
import com.model.BlogPost;
import com.model.Friend;
import com.model.Job;
import com.model.ProfilePicture;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author shrey
 */
@Configuration
@ComponentScan("com")
@EnableTransactionManagement
public class DbConfig {
        @Bean
		public SessionFactory sessionFactory() {
			LocalSessionFactoryBuilder lsf=
					new LocalSessionFactoryBuilder(getDataSource());
			Properties hibernateProperties=new Properties();
			hibernateProperties.setProperty(
					"hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
//    		hibernateProperties.setProperty(
//					"hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
			hibernateProperties.setProperty("hibernate.show_sql", "true");
			lsf.addProperties(hibernateProperties);
			Class classes[]=new Class[]{User.class,BlogPost.class,Job.class,Friend.class,ProfilePicture.class};
		    return lsf.addAnnotatedClasses(classes).buildSessionFactory();
		}
		    @Bean
		    public DataSource getDataSource() {
 	        BasicDataSource dataSource = new BasicDataSource();
		    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		    dataSource.setUsername("SYSTEM");
		    dataSource.setPassword("Shreykhanna07");
		    return dataSource;
		}
		@Bean
		public HibernateTransactionManager hibTransManagement(){
			return new HibernateTransactionManager(sessionFactory());
		}

}
