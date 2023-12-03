package dev.jjk.portfoliogallery.features.contents.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ContentsAddDto {

	private String title;
	private String contents;
	private String thumb;
}
