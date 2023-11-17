package dev.jjk.portfoliogallery.features.contents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.jjk.portfoliogallery.features.contents.dto.ContentsAddDto;
import dev.jjk.portfoliogallery.features.contents.repository.ContentsRepository;

@Service
public class ContentsService {
	@Autowired
	private ContentsRepository contentsRepository;

	@Transactional
	public String ContentsAdd(ContentsAddDto contentsDto) {
		contentsRepository.save(contentsDto.convertToEntity());

		System.out.println("현재 데이터베이스 내에 삽입된 갯수 : " + contentsRepository.count());

		return "Successfully added";
	}
}
