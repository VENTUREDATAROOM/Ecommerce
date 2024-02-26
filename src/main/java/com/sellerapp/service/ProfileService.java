package com.sellerapp.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sellerapp.entity.ProfileEntity;
import com.sellerapp.model.ProfileDTO;
import com.sellerapp.repository.ProfileRepo;
@Service
public class ProfileService {
	 @Autowired
	 private ProfileRepo profileRepo;
	 
	 private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProfileService.class);

	public String saveProfile(ProfileDTO pro)
	{
		try
		{
		ProfileEntity profileEntity=new ProfileEntity();
		profileEntity.setDateofbirth(pro.getDateofbirth());
		profileEntity.setProfilePic(pro.getProfilePic());
		profileRepo.save(profileEntity);
		return "Success";
		} catch(Exception e)
		{
			log.error("Error occurred while saving pancard details: \" + e.getMessage(), e");
			return "Error";
		}
	}

}