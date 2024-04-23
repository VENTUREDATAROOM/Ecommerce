package com.sellerapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

	
	
	@RequestMapping("/api")
	public String index()
	{
		return "index";
	}
}
