package net.jun.toyblog.service;

import net.jun.toyblog.domain.Post;
import net.jun.toyblog.domain.User;
import net.jun.toyblog.repository.PostRepository;
import net.jun.toyblog.repository.UserRepository;
import net.jun.toyblog.service.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;

	@Autowired
	public PostService(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

	public void save(PostDto postDto) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepository.findByUsername(username);

		Post post = Post.builder()
				.user(user)
				.title(postDto.getTitle())
				.content(postDto.getContent())
				.build();

		postRepository.save(post);
	}
}
