package net.jun.toyblog.security;

import net.jun.toyblog.domain.User;
import net.jun.toyblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by wayne on 2017. 12. 13..
 *
 */
@Component
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		return Optional.ofNullable(user)
				.map(CustomUserDetails::from)
				.orElseThrow(() -> new UsernameNotFoundException("could not found user : " + username));
	}
}
