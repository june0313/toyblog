package net.jun.toyblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class AuthController {

	@GetMapping("/signIn")
	public String signIn(String error, Model model) {
		Optional.ofNullable(error).ifPresent(e -> model.addAttribute("error", true));
		return "signIn";
	}
}
