package com.promising.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public void home(Model model) {
		model.addAttribute("hello", "안녕하세요, 반갑습니다!");
	}

}
