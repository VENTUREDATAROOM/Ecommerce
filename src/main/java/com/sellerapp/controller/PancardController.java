package com.sellerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sellerapp.model.PancardDTO;
import com.sellerapp.model.Response2;
import com.sellerapp.service.PancardService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "Pancard-API")
public class PancardController {

	// private org.slf4j.Logger log =
	// org.slf4j.LoggerFactory.getLogger(PancardController.class);

	@Autowired
	PancardService pancardService;

	/*
	 * @PostMapping(value="/uploadPancard",consumes=MediaType.APPLICATION_JSON_VALUE
	 * ,produces=MediaType.APPLICATION_JSON_VALUE)
	 *
	 * @Operation(summary ="upload pan card details  through pan number") public
	 * ResponseEntity<?> uploadPancardPhoto(@RequestBody PancardDTO pan) {
	 *
	 * g
	 *
	 * String responsesave=pancardService.savePancardPhoto(pan);
	 * if("Success".equals(responsesave)) { return
	 * Response2.generateResponse("Pancard details are uploaded",HttpStatus.OK,"200"
	 * );
	 *
	 * } else if("Error".equals(responsesave)) { return
	 * Response2.generateResponse("Error", HttpStatus.BAD_REQUEST, "400"); } else {
	 * return Response2.generateResponse("Pan card details are not get uploaded",
	 * HttpStatus.INTERNAL_SERVER_ERROR, "500");
	 *
	 * } }
	 */
	/*
	 * @PostMapping(value
	 * ="/verifyPancard",consumes=MediaType.APPLICATION_JSON_VALUE,produces=
	 * MediaType.APPLICATION_JSON_VALUE)
	 *
	 * @Operation(summary="verify pan card number") public ResponseEntity<?>
	 * verifyPancard(@RequestBody PancardDTO pan) { boolean isValid =
	 * pancardService.verifyPancardNumber(pan); if (isValid) { return
	 * Response2.generateResponse("Pancard details are verified", HttpStatus.OK,
	 * "200"); } else { return Response2.generateResponse("Invalid PAN card number",
	 * HttpStatus.BAD_REQUEST, "400"); } }
	 */
	@PostMapping("/api/v1/pan/mobile-to-pan")
	public ResponseEntity<?> getPanFromMobile(@RequestBody PancardDTO pan) {
		String res = pancardService.getPanFromMobile(pan);
		if (res != null) {
			return Response2.generateResponse(res, HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}
	}

	@PostMapping(value = "/uploadPanCard")
	public ResponseEntity<?> getPanCardNumber(@RequestParam("panCardNumber") String panCardNumber,
			@RequestParam("panCardImage") MultipartFile panCardImage) {

		if (!panCardImage.isEmpty() && panCardNumber != null) {
			if (panCardNumber.length() == 10) {
				return new ResponseEntity<String>("Verified your Pan Card !", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Error ! please check you PanNumber", HttpStatus.BAD_GATEWAY);
			}
		} else {
			return new ResponseEntity<String>("Error ! please check you data", HttpStatus.BAD_GATEWAY);
		}
	}

}
