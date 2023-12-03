package dev.jjk.util.api;

public class ApiUtils {
	public static ApiResult.ApiResultBuilder success() {
		return ApiResult.builder().success(true);
	}

	public static ApiResult error(String errorCode, String message) {
		return ApiResult.builder()
			.success(false)
			.error(errorCode, message)
			.build();
	}
}
