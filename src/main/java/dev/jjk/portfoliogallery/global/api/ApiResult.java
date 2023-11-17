package dev.jjk.portfoliogallery.global.api;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@Builder
public class ApiResult {
	private final boolean success;

	@Singular("data")
	private final Map<String, Object> responseData;

	@Singular("error")
	private final Map<String, String> error;

	// success 메서드: 성공 결과를 생성
	public static ApiResult success() {
		return ApiResult.builder()
			.success(true)
			.build();
	}

	// error 메서드: 에러 결과를 생성
	public static ApiResult error() {
		return ApiResult.builder()
			.success(false)
			.build();
	}
}
