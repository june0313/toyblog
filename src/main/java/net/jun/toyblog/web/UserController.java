package net.jun.toyblog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.jun.toyblog.domain.User;
import net.jun.toyblog.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/signUp")
	public String signUpView() {
		return "signUp";
	}
	
	@PostMapping("/signUp")
	public String signUp(User user) {
		userService.saveUser(user);
		return "redirect:/";
	}

}
