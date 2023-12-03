package dev.jjk.portfoliogallery.features.user.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserDTO {

	@Id
	@GeneratedValue
	private long Id;
	private String email;
	private String password;
	private String displayName;
}
