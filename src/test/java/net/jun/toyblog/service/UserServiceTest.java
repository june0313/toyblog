package net.jun.toyblog.service;

import net.jun.toyblog.domain.User;
import net.jun.toyblog.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	@Autowired 
	private UserService userService;
	private User user;

	@Before
	public void setup() {
		user = new User();
		user.setUsername("june0313");
		user.setPassword("password");
		user.setEmail("june0313@test.com");
	}
	
	@Test
	public void saveUser() {
		// when
		User savedUser = userService.saveUser(user);
		
		// then
		assertThat(savedUser.getId()).isNotNull().isEqualTo(1L);
		assertThat(savedUser.getUsername()).isEqualTo("june0313");
		assertThat(savedUser.getEmail()).isEqualTo("june0313@test.com");
		assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
		assertThat(savedUser.getRoles()).isNotNull().isNotEmpty().hasSize(1);
	}

}
