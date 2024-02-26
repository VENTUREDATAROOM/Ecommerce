package com.sellerapp.service;




import java.util.Base64;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import org.springframework.web.multipart.MultipartFile;

import com.sellerapp.entity.AadharEntity;
import com.sellerapp.model.AadharDTO;

import com.sellerapp.repository.AadharRepository;
@Service
public class AadharService {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AadharService.class);
	
	@Autowired
	AadharRepository aadharRepo;
	
	public String saveAadharDetails(AadharDTO aadhar) {
	    try {
	       // AadharEntity aadharEntity = aadharRepo.findByAadharNumber(aadhar.getAadharNumber());
	        /*if (aadharEntity == null) {
	            aadharEntity = new AadharEntity();
	            aadharEntity.setAadharNumber(aadhar.getAadharNumber());
	        }*/
           
	        String frontPagefilename= aadhar.getFrontPage().getOriginalFilename();
	        Base64.getEncoder().encodeToString(aadhar.getFrontPage().getBytes());
	       // byte[] frontPageBytes = frontPage.getBytes();
	        //String frontPageBase64 = Base64.getEncoder().encodeToString(frontPageBytes);
	        //aadharEntity.setFrontPage(frontPageBase64);

	        //MultipartFile backPage = aadhar.getBackPage(); 
	        //byte[] backPageBytes = backPage.getBytes();
	        //String backPageBase64 = Base64.getEncoder().encodeToString(backPageBytes);
	        //aadharEntity.setBackPage(backPageBase64);

	        //aadharRepo.save(aadharEntity);
	        System.out.println("The aadhar details are saved: " +frontPagefilename);
	        //log.info("Aadhar details saved successfully");
	        return "Success";
	    } 
	     catch (Exception e) {
	    	 e.printStackTrace();
	        log.error("Exception while saving the Aadhar card photo: " + e.getMessage(),e);
	        return "Error";
	    }
	}

	public boolean verifyAadharNumber(AadharDTO aadhar)
	{
		return aadhar!=null && aadhar.getAadharNumber()!=null && aadhar.getAadharNumber().length()==12;
	
	}
}
