package dev.jjk.portfoliogallery.test;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class HelloDSL {
	@Id
	@GeneratedValue
	private Long id;
}
