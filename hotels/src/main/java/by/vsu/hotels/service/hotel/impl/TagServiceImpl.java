package by.vsu.hotels.service.hotel.impl;

import by.vsu.hotels.model.Tag;
import by.vsu.hotels.repo.TagRepository;
import by.vsu.hotels.service.BaseServiceImpl;
import by.vsu.hotels.service.hotel.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends BaseServiceImpl<Tag, TagRepository> implements TagService {

    public TagServiceImpl(TagRepository repository) {
        super(repository);
    }

    @Override
    public Tag findByName(String name) {
        return getRepository().findByName(name);
    }
}
