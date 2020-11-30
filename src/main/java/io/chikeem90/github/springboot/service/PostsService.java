package io.chikeem90.github.springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.chikeem90.github.springboot.domain.posts.Posts;
import io.chikeem90.github.springboot.domain.posts.PostsRepository;
import io.chikeem90.github.springboot.web.dto.PostsResponseDto;
import io.chikeem90.github.springboot.web.dto.PostsSaveRequestDto;
import io.chikeem90.github.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostsService {
	private final PostsRepository postsRepository;

	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		posts.update(requestDto.getTitle(), requestDto.getContent());
		return id;
	}

	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		return new PostsResponseDto(entity);
	}
}
