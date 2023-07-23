package dev.jjk.portfoliogallery.temp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HelloDSL {
    @Id
    @GeneratedValue
    private Long id;
}