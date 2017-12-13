package net.jun.toyblog.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Getter
public class Role {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;

}
