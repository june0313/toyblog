package net.jun.toyblog.repository;

import net.jun.toyblog.domain.Post;
import net.jun.toyblog.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

	@Autowired private TestEntityManager entityManager;
	@Autowired private PostRepository postRepository;

	private User user;

	@Before
	public void setUp() {
		user = new User(null, "user", "password", "email", null);
		entityManager.persist(user);
		Post post = new Post(null, "title", "content", user, LocalDateTime.now(), LocalDateTime.now());
		entityManager.persist(post);
	}

	@Test
	public void findOnePost() {
		Post post = postRepository.findOne(1L);

		assertThat(post.getId()).isNotNull().isEqualTo(1L);
		assertThat(post.getTitle()).isNotNull().isEqualTo("title");
		assertThat(post.getContent()).isNotNull().isEqualTo("content");
		assertThat(post.getUser()).isNotNull();
	}

	@Test
	public void findAllByUserAndFirstPage() {
		Page<Post> postPage = postRepository.findAllByUser(user, new PageRequest(0, 1));
		assertThat(postPage).isNotNull().hasSize(1);
	}

	@Test
	public void findAllByUserAndSecondPage() {
		Page<Post> postPage = postRepository.findAllByUser(user, new PageRequest(1, 1));
		assertThat(postPage).isNotNull().hasSize(0);
	}
}