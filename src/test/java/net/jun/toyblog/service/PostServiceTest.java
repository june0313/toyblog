package net.jun.toyblog.service;

import net.jun.toyblog.domain.Post;
import net.jun.toyblog.domain.User;
import net.jun.toyblog.repository.PostRepository;
import net.jun.toyblog.repository.UserRepository;
import net.jun.toyblog.service.dto.PostDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class PostServiceTest {

	@Mock private PostRepository postRepository;
	@Mock private UserRepository userRepository;
	@InjectMocks private PostService postService;

	private User dummyUser;
	private Post dummyPost;

	@Before
	public void setUp() {
		dummyUser = new User(1L, "test-user", "password", "email@email.com", null);
		dummyPost = Post.builder()
				.id(1L)
				.title("title")
				.content("content")
				.createdDate(LocalDateTime.now())
				.lastModifiedDate(LocalDateTime.now())
				.user(dummyUser)
				.build();
	}

	@Test
	public void save() {
	}

	@Test
	public void find() {
		given(postRepository.findOne(1L)).willReturn(dummyPost);

		PostDto postDto = postService.find(1L);

		assertThat(postDto).isNotNull();
		assertThat(postDto.getId()).isEqualTo(1L);
		assertThat(postDto.getTitle()).isEqualTo("title");
		assertThat(postDto.getContent()).isEqualTo("content");
		assertThat(postDto.getWriter()).isEqualTo("test-user");
	}
}