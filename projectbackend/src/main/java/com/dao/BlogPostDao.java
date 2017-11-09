package com.dao;

import java.util.List;

import com.model.BlogPost;

public interface BlogPostDao {
void saveBlogPost(BlogPost blogPost);
public List<BlogPost> getAllBlogs(int approved);
public BlogPost getBlogPost(int id);
void updateBlogPost(BlogPost blogPost);
public List<BlogPost> getApprovalStatus(String username);

}