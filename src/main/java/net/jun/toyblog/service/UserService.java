package net.jun.toyblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.jun.toyblog.domain.Role;
import net.jun.toyblog.domain.User;
import net.jun.toyblog.repository.RoleRepository;
import net.jun.toyblog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = roleRepository.findByName("USER");
		user.addRole(role);
		return userRepository.save(user);
	}
	
}
