package com.sellerapp.controller;

import java.util.HashMap;
//import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sellerapp.config.JwtTokenUtil;
import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.model.JwtRequest;
import com.sellerapp.model.JwtResponse;
import com.sellerapp.model.JwtWithUserCodeResponse;
import com.sellerapp.model.Response2;
import com.sellerapp.model.ResponseForToken;
import com.sellerapp.model.ResponseTokennn;
import com.sellerapp.model.ResponseWithObject;
import com.sellerapp.repository.GdmsApiRepository;
import com.sellerapp.service.JwtUserDetailsService;
import com.sellerapp.service.MobileService;

import io.jsonwebtoken.impl.DefaultClaims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@Tag(name = "Login-API")
public class JwtAuthenticationController {
	
	
	@Autowired
	private GdmsApiRepository gdmsRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private MobileService mobileService;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private UserDetailsService userDetailsService;

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JwtAuthenticationController.class);

	@PostMapping(value = "/authenticatebyjson")
	@Operation(summary = "login apip for the user  for the login ")
	public ResponseEntity<?> createAuthenticationTokenWithPath(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		log.info("from by json variable login usrname:{} {}", authenticationRequest.getMobileNumber(),
				authenticationRequest.getPassword());
		authenticate(authenticationRequest.getMobileNumber(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getMobileNumber());

		final String token = jwtTokenUtil.generateToken(userDetails);

		if (token != null) {
			return ResponseForToken.generateResponse(token, HttpStatus.OK, "200");
		} else {
			return ResponseForToken.generateResponse(" ", HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}
	}
	@PostMapping(value = "/verifyOtp")
	@Operation(summary = "Username,password and OTP in JSON format  is required ")
	public ResponseEntity<?> authenticatebyjsonnew(@RequestBody JwtRequest authenticationRequest) throws Exception {
		try {
			authenticate(authenticationRequest.getMobileNumber(), authenticationRequest.getPassword());
			System.out.println("for login usrname:" + authenticationRequest.getMobileNumber() + "$$REQUESTBODYJSON$$"
					+ authenticationRequest.getPassword() + "otp:-" + authenticationRequest.getOtpgen());

			final UserDetails userDetails = jwtUserDetailsService.loadUserByOtp(authenticationRequest.getMobileNumber(),
					authenticationRequest.getOtpgen());
			
			
			final String token = jwtTokenUtil.generateToken(userDetails);
			jwtUserDetailsService.setloginHistory(authenticationRequest, token);
			mobileService.restOtp(authenticationRequest);
		
			
			Optional<GdmsApiUsers> userOptional = this.gdmsRepository.findByMobileNumber(authenticationRequest.getMobileNumber());
            
			GdmsApiUsers dataOfuser = userOptional.get();
	
			if (token != null) {
				JwtWithUserCodeResponse r=new JwtWithUserCodeResponse();
				r.setToken(token);
				r.setUserCode(dataOfuser.getUserCode());
				//return new ResponseEntity<JwtWithUserCodeResponse>(r,HttpStatus.OK);
				//return ResponseForToken.generateResponse(token,HttpStatus.OK,"200");
				return ResponseTokennn.generateResponse(r,HttpStatus.OK,"200");
			} else {
				return ResponseForToken.generateResponse("", HttpStatus.INTERNAL_SERVER_ERROR, "500");
			}
		} catch (Exception e) {
			return Response2.generateResponse("INVALID_CREDENTIALS ", HttpStatus.UNAUTHORIZED, "000");
		}
	}


	/*@PostMapping(value = "/verifyOtp")
	@Operation(summary = "Username,password and OTP in JSON format  is required ")
	public ResponseEntity<?> authenticatebyjsonnew(@RequestBody JwtRequest authenticationRequest) throws Exception {
		try {
			authenticate(authenticationRequest.getMobileNumber(), authenticationRequest.getPassword());
			System.out.println("for login usrname:" + authenticationRequest.getMobileNumber() + "$$REQUESTBODYJSON$$"
					+ authenticationRequest.getPassword() + "otp:-" + authenticationRequest.getOtpgen());

			final UserDetails userDetails = jwtUserDetailsService.loadUserByOtp(authenticationRequest.getMobileNumber(),
					authenticationRequest.getOtpgen());
			
			
			final String token = jwtTokenUtil.generateToken(userDetails);
			jwtUserDetailsService.setloginHistory(authenticationRequest, token);
			mobileService.restOtp(authenticationRequest);
		
			
			Optional<GdmsApiUsers> userOptional = this.gdmsRepository.findByMobileNumber(authenticationRequest.getMobileNumber());
            
			GdmsApiUsers dataOfuser = userOptional.get();
	
			if (token != null) {
				JwtWithUserCodeResponse r=new JwtWithUserCodeResponse();
				r.setToken(token);
				r.setUserCode(dataOfuser.getUserCode());
				return new ResponseEntity<JwtWithUserCodeResponse>(r,HttpStatus.OK);
				//return ResponseForToken.generateResponse(token,HttpStatus.OK,"200");
			} else {
				return ResponseForToken.generateResponse("", HttpStatus.INTERNAL_SERVER_ERROR, "500");
			}
		} catch (Exception e) {
			return Response2.generateResponse("INVALID_CREDENTIALS ", HttpStatus.UNAUTHORIZED, "000");
		}
	}*/

	@PostMapping(value = "/loginByOtp", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, "image/jpeg",
			"image/png" }, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Genration of the OTP for login and registration")
	public ResponseEntity<?> otpGenerator(@ModelAttribute JwtRequest authenticationRequest) throws Exception {
		try {
			Integer otp = mobileService.otpGenerator(authenticationRequest);
			if (otp > 1) {
				authenticationRequest.setOtpgen(otp.toString());
				authenticationRequest.setMobileNumber(authenticationRequest.getMobileNumber());

				return new ResponseWithObject().generateResponse("success", HttpStatus.OK, "200",
						authenticationRequest);
			} else if (otp == 1) {
				authenticationRequest.setOtpgen(null);
				return new ResponseWithObject().generateResponse("WRONG PASSWORD", HttpStatus.UNAUTHORIZED, "1002",
						authenticationRequest);
			} else {
				authenticationRequest.setOtpgen(null);
				return new ResponseWithObject().generateResponse("UNAUTHORIZED", HttpStatus.UNAUTHORIZED, "1003", null);
			}
		} catch (

		Exception e) {
			return ResponseForToken.generateResponse("parameter does not matched", HttpStatus.BAD_REQUEST, "400");
		}
	}

	@PostMapping(value = "/resetOtp")
	@Operation(summary = "Reset of the OTP for login and registration")
	public ResponseEntity<?> resetOtp(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception {
		try {

			Integer resetOtp = mobileService.restOtp(authenticationRequest);
			if (resetOtp > 0) {
				return new ResponseWithObject().generateResponse("Reset otp", HttpStatus.OK, "", authenticationRequest);
			} else {
				return new ResponseWithObject().generateResponse("UNAUTHORIZED", HttpStatus.UNAUTHORIZED, "",
						authenticationRequest);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseForToken.generateResponse("paramter does not matched", HttpStatus.BAD_REQUEST, "400");
		}
	}

	@GetMapping(value = "/refreshtoken")
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
		if (claims == null) {
			return Response2.generateResponse("Token is already valid ", HttpStatus.UNAUTHORIZED, "000");
		} else {
			Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
			String token = jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
			return ResponseForToken.generateResponse(token, HttpStatus.OK, "200");
		}
	}

	private void authenticate(String mobileNumber, String password) {
		Objects.requireNonNull(mobileNumber);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mobileNumber, password));
		} catch (DisabledException e) {
			throw new DisabledException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new UsernameNotFoundException("INVALID_CREDENTIALS", e);
		}
	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}

}
