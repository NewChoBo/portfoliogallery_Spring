package dev.jjk.portfoliogallery.features.portfolio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PortfolioDto {
	private Long portfolioId;
	private String userId;
	private String thum;
}
