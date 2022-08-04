package by.vsu.hotels.service.hotel;

import by.vsu.hotels.model.Tag;
import by.vsu.hotels.service.BaseService;

public interface TagService extends BaseService<Tag> {

    Tag findByName(String name);

}
