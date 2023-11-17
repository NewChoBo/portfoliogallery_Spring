package dev.jjk.portfoliogallery.features.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jjk.portfoliogallery.features.portfolio.dto.PortfolioDto;
import dev.jjk.portfoliogallery.features.portfolio.service.PortfolioService;
import dev.jjk.portfoliogallery.global.api.ApiResult;

@RestController
@RequestMapping("/portfolio")
public class PortfolioCont {
	@Autowired
	private PortfolioService portfolioService;

	@CrossOrigin(origins = "*")
	@GetMapping("/list")
	public ApiResult getPortfolios(Pageable pageable) {
		return portfolioService.getPortfolioList(pageable);
	}

	@PostMapping("/")
	public ApiResult addPortfolio(@RequestBody PortfolioDto portfolioDto) {
		return portfolioService.addPortfolio(portfolioDto);
	}

	@DeleteMapping("/{portfolioId}")
	public ApiResult deletePortfolio(@PathVariable("portfolioId") long portfolioId) {
		return portfolioService.deletePortfolio(portfolioId);
	}

	@PutMapping("/{portfolioId}")
	public ApiResult updatePortfolio(@RequestBody PortfolioDto portfolioDto) {
		return portfolioService.updatePortfolio(portfolioDto);
	}

	@GetMapping("/{portfolioId}")
	public ApiResult getPortfolio(@PathVariable("portfolioId") long portfolioId) {
		return portfolioService.getPortfolio(portfolioId);
	}
}
