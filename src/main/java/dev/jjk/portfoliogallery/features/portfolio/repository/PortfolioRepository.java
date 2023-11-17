package dev.jjk.portfoliogallery.features.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jjk.portfoliogallery.features.portfolio.entity.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
