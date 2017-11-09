package com.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureDao 
{
	
	void save(ProfilePicture profilePicture);
	ProfilePicture getProfilePic(String username);
}