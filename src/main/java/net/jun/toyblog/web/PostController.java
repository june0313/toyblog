package net.jun.toyblog.web;

import net.jun.toyblog.service.PostService;
import net.jun.toyblog.service.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/new")
	public String newPost() {
		return "write";
	}

	@GetMapping("/{id}")
	public String post(@PathVariable long id, Model model) {
		PostDto postDto = postService.find(id);
		model.addAttribute("post", postDto);
		return "postDetail";
	}

	@PostMapping
	public String savePost(PostDto postDto) {
		postService.save(postDto);
		return "redirect:/";
	}
	
}
