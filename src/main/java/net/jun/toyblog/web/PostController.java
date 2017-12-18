package net.jun.toyblog.web;

import net.jun.toyblog.service.PostService;
import net.jun.toyblog.service.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/{id}/edit")
	public String editView(@PathVariable long id, Model model) {
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		PostDto postDto = postService.find(id);

		if (!postDto.getWriter().equals(loginUsername)) {
			throw new AccessDeniedException("access denied");
		}

		model.addAttribute("post", postDto);
		return "edit";
	}

	@PostMapping("/{id}/edit")
	public String edit(@PathVariable long id, PostDto postDto) {
		postService.update(id, postDto);
		return "redirect:/posts/" + id;
	}

	@PostMapping
	public String savePost(PostDto postDto) {
		postService.save(postDto);
		return "redirect:/";
	}

	@PostMapping("{id}/delete")
	public String delete(@PathVariable long id) {
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		PostDto postDto = postService.find(id);

		if (!postDto.getWriter().equals(loginUsername)) {
			throw new AccessDeniedException("access denied");
		}

		postService.delete(id);
		return "redirect:/";
	}

	@ExceptionHandler(value = {AccessDeniedException.class})
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public String accessDenied() {
		return "forbidden";
	}
	
}
