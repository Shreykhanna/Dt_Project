package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.BlogPost;
@Repository
@Transactional
public class BlogPostDaoImpl implements BlogPostDao {
	@Autowired
private SessionFactory sessionFactory;
	public void saveBlogPost(BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blogPost);

	}
	public List<BlogPost> getAllBlogs(int approved) {
		System.out.println("Entered getallblogs method"+approved);
		Session session=sessionFactory.getCurrentSession();
		Query query=null;
		if(approved==1)
		{
			System.out.println("inside getallblogs method if loop");
		 query=session.createQuery("from BlogPost where approved="+approved);
		}
		else  if(approved==0)
		{	
			System.out.println("inside getallblogs method if else loop");
			query=session.createQuery("from BlogPost where approved=0 and rejectionReason is null");
		}
		System.out.println("left getallblogs method");
		return query.list();
		
	}
	public BlogPost getBlogPost(int id) {
		Session session=sessionFactory.getCurrentSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
		return blogPost;
	}
	public void updateBlogPost(BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		session.update(blogPost);//update blogpost set approved=?.... where id=?
		
	}
	public List<BlogPost> getApprovalStatus(String username) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where postedBy.username=? and viewedStatus=? and (approved=1 or rejectionreason!=null)");
		query.setString(0, username);
		query.setBoolean(1, false);
		List<BlogPost> blogPosts=query.list();
		for(BlogPost bp:blogPosts){
			bp.setViewedStatus(true);			
			session.update(bp);
		}
		return blogPosts;
	}

}
