package dev.jjk.portfoliogallery.features.contents.service;

import dev.jjk.portfoliogallery.features.contents.dto.ContentsAddDto;
import dev.jjk.portfoliogallery.features.contents.entity.Contents;
import dev.jjk.portfoliogallery.features.contents.repository.ContentsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContentsService {

	@Autowired
	private ContentsRepository contentsRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Transactional
	public String ContentsAdd(ContentsAddDto contentsDto) {
		contentsRepository.save(modelMapper.map(contentsDto, Contents.class));

		System.out.println("현재 데이터베이스 내에 삽입된 갯수 : " + contentsRepository.count());

		return "Successfully added";
	}
}
