package net.jun.toyblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

	@GetMapping("/write")
	public String write() {
		return "write";
	}
	
}
