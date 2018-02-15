package net.jun.toyblog.web.interceptor;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthInterceptorTest {

	private AuthInterceptor sut;
	private ModelAndView modelAndView;

	@Before
	public void setUp() {
		sut = new AuthInterceptor();
		modelAndView = new ModelAndView();
	}

	@Test
	public void testWhenAuthenticationIsNull() {
		SecurityContextHolder.setContext(new SecurityContextImpl());

		// when
		sut.postHandle(null, null, null, modelAndView);

		// then
		assertThat(modelAndView.getModelMap()).containsKeys("isUserSignedIn", "username");
		assertThat(modelAndView.getModelMap().get("isUserSignedIn")).isEqualTo(false);
		assertThat(modelAndView.getModelMap().get("username")).isEqualTo("anonymousUser");
	}

	@Test
	public void testWhenUserIsNotSignedIn() {
		// given
		Authentication mockAuthentication = mock(AnonymousAuthenticationToken.class);
		when(mockAuthentication.getName()).thenReturn("anonymousUser");

		SecurityContextImpl context = new SecurityContextImpl();
		context.setAuthentication(mockAuthentication);

		SecurityContextHolder.setContext(context);

		// when
		sut.postHandle(null, null, null, modelAndView);

		// then
		assertThat(modelAndView.getModelMap()).containsKeys("isUserSignedIn", "username");
		assertThat(modelAndView.getModelMap().get("isUserSignedIn")).isEqualTo(false);
		assertThat(modelAndView.getModelMap().get("username")).isEqualTo("anonymousUser");
	}

	@Test
	public void testWhenUserIsSignedIn() {
		// given
		Authentication mockAuthentication = mock(UsernamePasswordAuthenticationToken.class);
		when(mockAuthentication.getName()).thenReturn("testUserName");

		SecurityContextImpl context = new SecurityContextImpl();
		context.setAuthentication(mockAuthentication);

		SecurityContextHolder.setContext(context);

		// when
		sut.postHandle(null, null, null, modelAndView);

		// then
		assertThat(modelAndView.getModelMap()).containsKeys("isUserSignedIn", "username");
		assertThat(modelAndView.getModelMap().get("isUserSignedIn")).isEqualTo(true);
		assertThat(modelAndView.getModelMap().get("username")).isEqualTo("testUserName");
	}
}
