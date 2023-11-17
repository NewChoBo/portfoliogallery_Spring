package dev.jjk.portfoliogallery.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/test")
@RestController
public class TestController {
	@Operation(summary = "테스트 API", description = "이 API는 테스트를 위한 API입니다.")
	@GetMapping("/testing_response")
	public String selectAllBookInfo() {
		return "api 호출 성공";
	}
}
