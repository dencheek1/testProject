package by.vsu.flight.service;

import by.vsu.flight.model.Tag;

public interface TagService extends BaseService<Tag> {

    Tag findByName(String name);

}
