package by.vsu.auth.rest;


import by.vsu.auth.model.Tag;
import by.vsu.auth.service.tag.TagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;

    @PostMapping("/add")
    public ResponseEntity<TagDto> addTag(TagDto tagDto) {
        TagDto tag  = TagDto.fromTag(tagService.save(tagDto.toTag()));
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<TagDto> getById(long id) {
        TagDto tagDto = TagDto.fromTag(tagService.findById(id));
        return new ResponseEntity<>(tagDto, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<TagDto> getByName(String name) {
        TagDto tagDto = TagDto.fromTag(tagService.findByName(name));
        return new ResponseEntity<>(tagDto, HttpStatus.OK);
    }

    @GetMapping("/tags")
    public ResponseEntity<List<TagDto>> getAll() {
        List<TagDto> tags = tagService.getAll().stream()
                .map(TagDto::fromTag)
                .collect(Collectors.toList());
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @PostMapping("/remove")
    public void delete(TagDto tagDto) {
        Tag tag = tagService.findByName(tagDto.getName());
        tagService.delete(tag);
    }
}
