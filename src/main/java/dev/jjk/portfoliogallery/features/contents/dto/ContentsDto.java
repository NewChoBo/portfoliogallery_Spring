package dev.jjk.portfoliogallery.features.contents.dto;

import dev.jjk.portfoliogallery.features.contents.entity.Contents;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentsDto {
	private Long id;
	private String title;
	private String contents;
	private String thumb;

	public Contents convertToEntity() {
		return Contents.builder()
			.id(id)
			.title(title)
			.contents(contents)
			.thumb(thumb)
			.build();
	}
}
