package net.jun.toyblog.web;

import net.jun.toyblog.service.PostService;
import net.jun.toyblog.service.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BlogController {

	private final PostService postService;

	@Autowired
	public BlogController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/@{username}")
	public String blogHome(@PathVariable String username, Pageable pageable, Model model) {
		Page<PostDto> postDtoPage = postService.findAllByUsername(username, pageable);
		model.addAttribute("posts", postDtoPage.getContent());
		return "blogHome";
	}

}
