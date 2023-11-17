package dev.jjk.portfoliogallery.features.portfolio.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PortfolioDto {
	private Long portfolioId;
	private String userId;
	private String thumb;
}
