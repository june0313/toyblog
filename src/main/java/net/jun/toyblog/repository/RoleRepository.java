package net.jun.toyblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.jun.toyblog.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);

}
