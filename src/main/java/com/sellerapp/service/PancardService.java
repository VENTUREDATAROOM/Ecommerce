package com.sellerapp.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.PancardEntity;
import com.sellerapp.model.PancardDTO;
import com.sellerapp.repository.PancardRepo;

@Service
public class PancardService {
	@Autowired
	private PancardRepo pancardRepo;

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PancardService.class);

	public String savePancardPhoto(PancardDTO pan) {
		try {
			PancardEntity pancardEntity = new PancardEntity();
			pancardEntity.setPancardNumber(pan.getPancardNumber());
			pancardEntity.setUserCode(pan.getUserCode());
			String pancardPageFileName = pan.getPic().getOriginalFilename();
			String basePan = Base64.getEncoder().encodeToString(pan.getPic().getBytes());
			byte[] panPageBytes = pan.getPic().getBytes();
			pancardEntity.setPic(panPageBytes);
			pan.setBasePan(basePan);
			pan.setPic(null);

			pancardRepo.save(pancardEntity);
			log.info("Pancard details are get successfully");
			return "Success";
		}

		catch (Exception e) {
			log.error("Error occurred while saving pancard details: ", e.getMessage());
			return "Error";
		}

	}

	/*
	 * public String getPanFromMobile(PancardDTO pan) { HttpHeaders header = new
	 * HttpHeaders();
	 *
	 * HttpEntity<PancardDTO> entity = new HttpEntity<>(pan, header);
	 * ResponseEntity<String> response =
	 * restTemplate.exchange("https://kyc-api.surepass.io/api/v1/pan/mobile-to-pan",
	 * HttpMethod.POST, entity, String.class); if (response.getStatusCode() ==
	 * HttpStatus.OK) {
	 *
	 * return response.getBody(); } else { return null; } }
	 */

	public boolean verifyPancardNumber(PancardDTO pan) {
		return pan != null && pan.getPancardNumber() != null && pan.getPancardNumber().length() == 10;
	}

}