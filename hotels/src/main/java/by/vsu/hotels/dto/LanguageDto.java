package by.vsu.hotels.dto;

import by.vsu.hotels.model.Language;
import lombok.Data;

@Data
public class LanguageDto {

    private String name;

    public Language toLanguage(){
        Language language = new Language();
        language.setName(name);
        return language;
    }

    public static LanguageDto toLanguageDto(Language language){
        if(language == null) {
            return null;
        }
        LanguageDto languageDto = new LanguageDto();
        languageDto.setName(language.getName());
        return languageDto;
    }

}
