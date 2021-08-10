package io.murad.blog.rest.service;

import io.murad.blog.rest.dto.TagDto;
import io.murad.blog.rest.exception.BlogRestException;
import io.murad.blog.rest.mapper.TagMapper;
import io.murad.blog.rest.model.Tag;
import io.murad.blog.rest.repository.TagRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public void saveTag(TagDto tagDto) {
        Tag saveTag = tagRepository.save(tagMapper.mapToTag(tagDto));
        log.info(saveTag.getTagName());
        saveTag.setTagId(tagDto.getId());
    }

    public List<TagDto> getAllTags() {
        return tagRepository.findAll()
                .stream()
                .map((tag) -> tagMapper.mapToTagDto(tag)).collect(Collectors.toList());
    }

    public TagDto getTag(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new BlogRestException("No Tag found with this id " + id));
        return tagMapper.mapToTagDto(tag);
    }
}
