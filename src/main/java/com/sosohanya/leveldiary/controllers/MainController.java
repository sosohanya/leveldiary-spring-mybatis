package com.sosohanya.leveldiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
    //이 부분 추가
	@GetMapping("/thymeleaf")
	public String thymeleaf() {
		return "thymeleaf"; //thymeleaf.html과 매핑
	}
}
