package net.jun.toyblog.web.interceptor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Optional;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		modelAndView.addObject("isUserSignedIn", isUserSignedIn());
		modelAndView.addObject("username", getUsername());
	}

	private Optional<Authentication> getAuthentication() {
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
	}

	private String getUsername() {
		return getAuthentication().map(Principal::getName).orElse("anonymousUser");
	}

	private boolean isUserSignedIn() {
		return getAuthentication()
				.map(UsernamePasswordAuthenticationToken.class::isInstance)
				.orElse(false);
	}
}
