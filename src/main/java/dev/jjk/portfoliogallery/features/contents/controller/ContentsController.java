package dev.jjk.portfoliogallery.features.contents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jjk.portfoliogallery.features.contents.dto.ContentsAddDto;
import dev.jjk.portfoliogallery.features.contents.service.ContentsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/contents")
public class ContentsController {
	@Autowired
	ContentsService contentsService;

	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully added content"),
		@ApiResponse(responseCode = "400", description = "Invalid input"),
		@ApiResponse(responseCode = "500", description = "Internal server error")})
	@PostMapping("/add")
	public String ContentsAdd(ContentsAddDto contentsDto) {
		contentsService.ContentsAdd(contentsDto);
		return null;
	}
}
