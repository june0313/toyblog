package net.jun.toyblog.service;

import net.jun.toyblog.domain.Post;
import net.jun.toyblog.domain.User;
import net.jun.toyblog.repository.PostRepository;
import net.jun.toyblog.repository.UserRepository;
import net.jun.toyblog.service.dto.PostDto;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class PostServiceTest {

	@Mock private PostRepository postRepository;
	@Mock private UserRepository userRepository;
	@InjectMocks private PostService postService;

	private User dummyUser;
	private Post dummyPost;
	private Page<Post> dummyPostPage;

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
		dummyPostPage = new PageImpl<>(Lists.newArrayList(dummyPost));
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

	@Test
	public void findAll() {
		given(postRepository.findAll(any(Pageable.class))).willReturn(dummyPostPage);

		Page<PostDto> postDtoPage = postService.findAll(new PageRequest(0, 20));

		ArgumentCaptor<Pageable> pageableArgument = ArgumentCaptor.forClass(Pageable.class);
		verify(postRepository, only()).findAll(pageableArgument.capture());
		assertThat(pageableArgument.getValue().getOffset()).isEqualTo(0);
		assertThat(pageableArgument.getValue().getPageSize()).isEqualTo(20);
		assertThat(postDtoPage).hasSize(1);
	}
}