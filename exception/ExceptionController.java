package com.sellerapp.exception;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.sellerapp.model.Response2;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class ExceptionController {

	/*@ExceptionHandler(value = IOException.class)

	public ResponseEntity<Object> handleGlobalException(Exception e, WebRequest request) {

		return Response2.generateResponse("wrong request", HttpStatus.INTERNAL_SERVER_ERROR, "006");

	}

	@ExceptionHandler(MultipartException.class)
	@ResponseBody
	ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {

		return Response2.generateResponse("missing parameter", HttpStatus.INTERNAL_SERVER_ERROR, "007");

	}

	@ExceptionHandler(MissingServletRequestPartException.class)
	@ResponseBody
	ResponseEntity<?> handleMissingServletRequestPartException(HttpServletRequest request, Throwable ex) {

		return Response2.generateResponse("Should select atleast file for submit", HttpStatus.INTERNAL_SERVER_ERROR,
				"009");

	}

	@ExceptionHandler(UnauthorisedException.class)
	@ResponseBody
	ResponseEntity<?> handleUnauthorisedException(HttpServletRequest request, Throwable ex) {

		return Response2.generateResponse("Request is un authorised", HttpStatus.BAD_REQUEST, "013");

	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	ResponseEntity<?> handleIllegalArgumentException(HttpServletRequest request, Throwable ex) {

		return Response2.generateResponse("Illegal arugement", HttpStatus.BAD_REQUEST, "014");

	}

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseBody
	ResponseEntity<?> handleUsernameNotFoundException(HttpServletRequest request, Throwable ex) {

		return Response2.generateResponse("INVALID_CREDENTIALS ", HttpStatus.UNAUTHORIZED, "1001");

	}

	@ExceptionHandler(RequestRejectedException.class)
	@ResponseBody
	ResponseEntity<?> handleRequestRejectedException(HttpServletRequest request, Throwable ex) {

		return Response2.generateResponse("INVALID_CREDENTIALS ", HttpStatus.UNAUTHORIZED, "000");

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HashMap<String, String>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {

		HashMap<String, String> hash = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((errorHash) -> {
			String feildname = ((FieldError) errorHash).getField();
			String message = errorHash.getDefaultMessage();
			hash.put(feildname, message);
		});

		return new ResponseEntity<>(hash, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpServletRequest request, Throwable ex) {

		return Response2.generateResponse("Wrong method type ", HttpStatus.METHOD_NOT_ALLOWED, "000");

	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	ResponseEntity<?> handleHttpMessageNotReadableException(HttpServletRequest request, Throwable ex) {

		return Response2.generateResponse(" Required request body is missing", HttpStatus.BAD_REQUEST, "000");

	}

	@ExceptionHandler(ExpiredJwtException.class)
	@ResponseBody
	ResponseEntity<?> handleExpiredJwtException(HttpServletRequest request, Throwable ex) {

		return Response2.generateResponse("authentication expired", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, "999");

	}

	@ExceptionHandler(value = { DisabledException.class })
	public ResponseEntity<?> handeleDisabledException(HttpServletRequest request, Throwable ex) {

		return Response2.generateResponse("User in locked ", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, "004");
	}

	@ExceptionHandler(value = { ServletException.class })
	public ResponseEntity<?> servletException(HttpServletRequest request, Throwable ex) {
		String message = ex.getMessage();
		if (message.equals("JWT Token has expired")) {
			message = "Authentication expired";
		}
		return Response2.generateResponse(message, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, "999");
	}*/

}
