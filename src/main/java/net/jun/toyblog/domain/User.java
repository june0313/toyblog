package net.jun.toyblog.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	public void addRole(Role role) {
		if (roles == null) {
			roles = new HashSet<>();
		}
		
		roles.add(role);
	}
}
