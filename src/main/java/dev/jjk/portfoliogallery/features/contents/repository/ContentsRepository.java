package dev.jjk.portfoliogallery.features.contents.repository;

import dev.jjk.portfoliogallery.features.contents.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepository extends JpaRepository<Contents, Long> {

}
