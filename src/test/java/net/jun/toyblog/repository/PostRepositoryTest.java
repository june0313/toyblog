package net.jun.toyblog.repository;

import net.jun.toyblog.domain.Post;
import net.jun.toyblog.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

	@Autowired private TestEntityManager entityManager;
	@Autowired private PostRepository postRepository;

	@Before
	public void setUp() {
		User user = new User(null, "user", "password", "email", null);
		Post post = new Post(null, "title", "content", user, LocalDateTime.now(), LocalDateTime.now());
		entityManager.persist(post);
	}

	@Test
	public void test() {
		Post post = postRepository.findOne(1L);

		assertThat(post.getId()).isNotNull().isEqualTo(1L);
		assertThat(post.getTitle()).isNotNull().isEqualTo("title");
		assertThat(post.getContent()).isNotNull().isEqualTo("content");
		assertThat(post.getUser()).isNotNull();
	}
}