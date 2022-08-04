package by.vsu.tour.dto;

import by.vsu.tour.model.Tag;
import lombok.Getter;
import lombok.Setter;

/**
 * Basic city data transfer object class.
 * Date:22/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@Setter
@Getter
public class TagDto {

    /**
     * Tag name.
     */
    private String name;

    /**
     * Convert the current tag DTO to a tag object.
     * @return Tag.
     */
    public Tag toTag() {
        Tag tag = new Tag();
        tag.setName(name);
        return tag;
    }

    /**
     * Create a tag data transfer object from a tag object.
     * @param tag
     * @return Tag DTO.
     */
    public static TagDto fromTag(Tag tag) {
        if (tag == null) {
            return null;
        }
        TagDto dto = new TagDto();
        dto.setName(tag.getName());
        return dto;
    }
}
