package com.sellerapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sellerapp.entity.PancardEntity;
import com.sellerapp.model.PancardDTO;
import com.sellerapp.repository.PancardRepo;

@Service
public class PancardService {
	@Autowired
	private PancardRepo pancardRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PancardService.class);


   /*	public String savePancardPhoto(PancardDTO pan)
	{
		try
		{
			PancardEntity pancardEntity=pancardRepo.findByPancardNumber(pan.getPancardNumber());
			if(pancardEntity==null)
			{
				pancardEntity=new PancardEntity();
				pancardEntity.setPancardNumber(pan.getPancardNumber());
			}
			
			pancardEntity.setPic(pan.getPic());
			pancardRepo.save(pancardEntity);
			log.info("Pancard details are get successfully");
			return "Success";
		}
		
		catch (Exception e) {
			log.error("Error occurred while saving pancard details: " + e.getMessage());
			return "Error";
		}

	}*/
	public String getPanFromMobile(PancardDTO pan)
	{
		HttpHeaders header=new HttpHeaders();
		

		HttpEntity<PancardDTO> entity=new HttpEntity<>(pan,header);
		ResponseEntity<String> response=restTemplate.exchange("https://kyc-api.surepass.io/api/v1/pan/mobile-to-pan", HttpMethod.POST, entity, String.class);
		if(response.getStatusCode()==HttpStatus.OK)
		{
		
			return response.getBody();
		}
		else
		{
			return null;
		}
	}
	
	/*public boolean verifyPancardNumber(PancardDTO pan)
	{
		return pan!=null && pan.getPancardNumber()!=null && pan.getPancardNumber().length()==10;
	}*/
}
