package net.jun.toyblog.web;

import net.jun.toyblog.service.PostService;
import net.jun.toyblog.service.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private final PostService postService;

	@Autowired
	public HomeController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping(value = {"/", "/posts"})
	public String home(Model model, Pageable pageable) {
		Page<PostDto> postDtoPage = postService.findAll(pageable);
		model.addAttribute("posts", postDtoPage.getContent());
		return "home";
	}

}
