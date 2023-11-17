package dev.jjk.portfoliogallery.features.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jjk.portfoliogallery.features.portfolio.dto.PortfolioDto;
import dev.jjk.portfoliogallery.features.portfolio.service.PortfolioService;

@RestController
public class PortfolioCont {
	@Autowired
	private PortfolioService portfolioService;

	@CrossOrigin(origins = "*")
	@GetMapping("/portfolios")
	public List<PortfolioDto> getPortfolios() {
		return portfolioService.getPortfolioList();
	}
}
