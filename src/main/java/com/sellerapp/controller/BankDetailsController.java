package com.sellerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.sellerapp.model.BankDetailsDTO;
import com.sellerapp.model.Response2;
import com.sellerapp.service.BankDetailsService;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "BankDetails-API")
public class BankDetailsController {
   @Autowired
	private BankDetailsService bankDetailsService;
	@PostMapping(value="uploadBankDetails",consumes=MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="upload bank details through bank account number")
	public ResponseEntity<?>  uploadBankDetails(@RequestBody BankDetailsDTO bankDetailsDTO)
	{
		
		String r=bankDetailsService.saveBankDetails(bankDetailsDTO);
		if("Success".equals(r))
		{
			return Response2.generateResponse("Bank details are saved", HttpStatus.OK, "200");
			
		}
		else if("Error".equals(r))
		{
			return Response2.generateResponse("Error", HttpStatus.BAD_REQUEST, "400");
			
		}
		else
		{
			return Response2.generateResponse("Bank details are not saved", HttpStatus.INTERNAL_SERVER_ERROR, "500");
			
		}
	}
}
