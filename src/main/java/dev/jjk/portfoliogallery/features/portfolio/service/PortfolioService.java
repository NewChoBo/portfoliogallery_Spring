package dev.jjk.portfoliogallery.features.portfolio.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.jjk.portfoliogallery.features.portfolio.dto.PortfolioDto;
import dev.jjk.portfoliogallery.features.portfolio.entity.Portfolio;
import dev.jjk.portfoliogallery.features.portfolio.entity.QPortfolio;
import dev.jjk.portfoliogallery.features.portfolio.repository.PortfolioRepository;
import dev.jjk.portfoliogallery.global.api.ApiResult;
import dev.jjk.portfoliogallery.global.api.ApiUtils;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private PortfolioRepository portfolioRepository;

	private JPAQueryFactory queryFactory;

	ModelMapper modelMapper = new ModelMapper();

	//QEntity
	QPortfolio portfolio = QPortfolio.portfolio;

	@PostConstruct
	private void init() {
		queryFactory = new JPAQueryFactory(entityManager);
	}

	// 데이터 조회 및 변환 메서드
	public ApiResult getPortfolioList(Pageable pageable) {
		List<PortfolioDto> portfolioDtoList = queryFactory.selectFrom(portfolio)
																											.offset(pageable.getOffset())
																											.limit(pageable.getPageSize())
																											.orderBy(portfolio.portfolioId.asc())
																											.stream()
																											.toList()
																											.stream()
																											.map(portfolio -> modelMapper.map(portfolio,
																													PortfolioDto.class))
																											.collect(Collectors.toList());
		long total = Optional.ofNullable(queryFactory.select(portfolio.count())
																								 .from(portfolio)
																								 .fetchOne())
												 .orElse(0L);

		return ApiUtils.success()
									 .data("page", pageable)
									 .data("total", total)
									 .data("list", portfolioDtoList)
									 .build();
	}

	public ApiResult addPortfolio(PortfolioDto portfolioDto) {
		portfolioRepository.save(modelMapper.map(portfolioDto, Portfolio.class));
		return ApiUtils.success()
									 .build();
	}

	public ApiResult deletePortfolio(long portfolioId) {
		long result = queryFactory.delete(portfolio)
															.where(portfolio.portfolioId.eq(portfolioId))
															.execute();
		if (result == 1) {
			return ApiUtils.success()
										 .build();
		} else {
			return ApiUtils.error("삭제 실패!", "삭제에 실패하였습니다.");
		}
	}

	public ApiResult updatePortfolio(PortfolioDto portfolioDto) {
		long result = queryFactory.update(portfolio)
															.set(portfolio.userId, portfolioDto.getUserId())
															.set(portfolio.thumb, portfolioDto.getThumb())
															.where(portfolio.portfolioId.eq(portfolioDto.getPortfolioId()))
															.execute();

		if (result == 1) {
			return ApiUtils.success()
										 .build();
		} else if (result == 0) {
			return ApiUtils.error("발견 실패!", "수정에 실패하였습니다.");
		} else {
			return ApiUtils.error("초과 수정!", "수정중에 알 수 없는 오류가 발생하였습니다.");
		}
	}

	public ApiResult getPortfolio(long portfolioId) {
		Portfolio portfolioEntity = queryFactory.selectFrom(portfolio)
																						.where(portfolio.portfolioId.eq(portfolioId))
																						.fetchOne();

		if (portfolioEntity == null) {
			return ApiUtils.error("발견 실패!", "Portfolio Not Found");
		}

		PortfolioDto portfolioDto = modelMapper.map(portfolioEntity, PortfolioDto.class);
		return ApiUtils.success()
									 .data("data", portfolioDto)
									 .build();
	}
}
