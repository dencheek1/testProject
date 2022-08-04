package by.vsu.hotels.rest;

import by.vsu.hotels.dto.LanguageDto;
import by.vsu.hotels.model.Language;
import by.vsu.hotels.service.geo.LanguageService;
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
@RequestMapping("/language")
public class LanguageController {

    private LanguageService service;

    @GetMapping("/all")
    public ResponseEntity<List<LanguageDto>> getAll(){
        List<Language> languages = service.getAll();
        List<LanguageDto> dtoList = languages.stream().map(LanguageDto::toLanguageDto).collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<Language> getByName(String name){
        Language language = service.findByName(name);
        return new ResponseEntity<>(language, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<LanguageDto> addLanguage(LanguageDto languageDto){
        Language language = service.findByName(languageDto.getName());
        if(language != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        language = languageDto.toLanguage();
        language = service.save(language);
        return new ResponseEntity<>(LanguageDto.toLanguageDto(language),HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delete(String name){
        Language language = service.findByName(name);
        if (language == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.delete(language);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
