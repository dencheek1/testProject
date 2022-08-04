package by.vsu.tour.service.tag;

import by.vsu.tour.model.Tag;
import by.vsu.tour.service.BaseService;

public interface TagService extends BaseService<Tag> {

    Tag findByName(String name);

}
