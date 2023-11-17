package dev.jjk.portfoliogallery.features.portfolio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.jjk.portfoliogallery.features.portfolio.dto.PortfolioDto;
import dev.jjk.portfoliogallery.features.portfolio.repository.PortfolioRepository;

@Service
public class PortfolioService {
	@Autowired
	private PortfolioRepository portfolioRepository;

	// 데이터 조회 및 변환 메서드
	public List<PortfolioDto> getPortfolioList() {
		return portfolioRepository.findAll()
			.stream()
			.map(portfolio -> PortfolioDto.builder()
				.userId(portfolio.getUserId())
				.portfolioId(portfolio.getPortfolioId())
				.thum(portfolio.getThum())
				.build())
			.collect(Collectors.toList());
	}
}
