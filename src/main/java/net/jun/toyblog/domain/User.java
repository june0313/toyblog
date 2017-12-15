package net.jun.toyblog.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
