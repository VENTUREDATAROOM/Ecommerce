package com.sellerapp.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.BankDetailsEntity;
import com.sellerapp.model.BankDetailsDTO;
import com.sellerapp.repository.BankRepo;

@Service
public class BankDetailsService {
	@Autowired
	private BankRepo bankRepo;
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BankDetailsService.class);

	public String saveBankDetails(BankDetailsDTO bankDetailsDTO) {
		try {
			
			BankDetailsEntity bankDetailsEntity = new BankDetailsEntity();
			bankDetailsEntity.setAccountNumber(bankDetailsDTO.getAccountNumber());
			bankDetailsEntity.setConfirmAccountNumber(bankDetailsDTO.getConfirmAccountNumber());
			bankDetailsEntity.setAccountType(bankDetailsDTO.getAccountType());
			bankDetailsEntity.setUserCode(bankDetailsDTO.getUserCode());
			bankDetailsEntity.setBankName(bankDetailsDTO.getBankName());
			bankDetailsEntity.setIfscCode(bankDetailsDTO.getIfscCode());
			byte[] bankDetailsBytes = Base64.getEncoder().encode(bankDetailsDTO.getBankDocument().getBytes());
			String bankBase64Image = Base64.getEncoder().encodeToString(bankDetailsDTO.getBankDocument().getBytes());
			bankDetailsDTO.setBaseBankImg(bankBase64Image);
			bankDetailsEntity.setBankDocument(bankDetailsBytes);
			bankRepo.save(bankDetailsEntity);

			return "Success";
		} catch (Exception e) {
			log.error("Error occurred while saving bank details: " + e.getMessage(), e);
			return "Error";
		}
	}

}