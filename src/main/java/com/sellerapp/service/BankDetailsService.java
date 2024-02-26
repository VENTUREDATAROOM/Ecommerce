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
	public String saveBankDetails(BankDetailsDTO bankDetailsDTO)
	{
		try
		{
		//BankDetailsEntity bankdetailsEntity=bankRepo.findByAccountNumber(bankdetailsDto.getAccountNumber());
		BankDetailsEntity bankdetailsEntity=new BankDetailsEntity();
		bankdetailsEntity.setAccountNumber(bankDetailsDTO.getAccountNumber());
		bankdetailsEntity.setConfirmAccountNumber(bankDetailsDTO.getConfirmAccountNumber());
		bankdetailsEntity.setAccountType(bankDetailsDTO.getAccountType());
		bankdetailsEntity.setBankName(bankDetailsDTO.getBankName());
		bankdetailsEntity.setIfscCode(bankDetailsDTO.getIfscCode());
		bankdetailsEntity.setBankDocument(bankDetailsDTO.getBankDocument());
		bankRepo.save(bankdetailsEntity);
		return "Success";
		 }
	  catch (Exception e) {
		log.error("Error occurred while saving bank details: " + e.getMessage(), e);
		return "Error";
	   }
	}

}
