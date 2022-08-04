package by.vsu.hotels.dto;

import by.vsu.hotels.model.Tag;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TagDto {

    private String name;

    public Tag toTag(){
        Tag tag = new Tag();
        tag.setName(name);
        return tag;
    }

    public static TagDto fromTag(Tag tag){
        if(tag == null) return null;
        TagDto dto = new TagDto();
        dto.setName(tag.getName());
        return dto;
    }
}
