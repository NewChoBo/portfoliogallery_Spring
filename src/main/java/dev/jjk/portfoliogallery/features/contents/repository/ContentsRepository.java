package dev.jjk.portfoliogallery.features.contents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jjk.portfoliogallery.features.contents.entity.Contents;

public interface ContentsRepository extends JpaRepository<Contents, Long> {
}
