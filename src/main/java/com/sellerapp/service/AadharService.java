package com.sellerapp.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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

			AadharEntity aadharEntity = new AadharEntity();
			aadharEntity.setAadharNumber(aadhar.getAadharNumber());
			// Encoding front page
			String frontPageFileName = aadhar.getFrontPage().getOriginalFilename();
			
			byte[] aadharFrontPageBytes = Base64.getEncoder().encode(aadhar.getFrontPage().getBytes());
			String baseFront = Base64.getEncoder().encodeToString(aadhar.getFrontPage().getBytes());
			aadharEntity.setFrontPage(aadharFrontPageBytes);
			// Encoding back Page
			String backPageFileName = aadhar.getBackPage().getOriginalFilename();
			
			byte[] aadharBackPageBytes = Base64.getEncoder().encode(aadhar.getBackPage().getBytes());
			String baseBack = Base64.getEncoder().encodeToString(aadhar.getBackPage().getBytes());
			aadharEntity.setBackPage(aadharBackPageBytes);
			aadharEntity.setUserCode(aadhar.getUserCode());
			aadhar.setBaseFront(baseFront);
			aadhar.setBaseBack(baseBack);
			aadhar.setBackPage(null);
			aadhar.setFrontPage(null);
			aadharRepo.save(aadharEntity);
			System.out.println("The aadhar details are saved: " + frontPageFileName + "," + backPageFileName);
			log.info("Aadhar details saved successfully");
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception while saving the Aadhar card photo: " + e.getMessage(), e);
			return "Error";
		}
	}

	public boolean verifyAadharNumber(AadharDTO aadhar) {
		return aadhar != null && aadhar.getAadharNumber() != null && aadhar.getAadharNumber().length() == 12;

	}
}