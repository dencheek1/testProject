package by.vsu.flight.service.impl;

import by.vsu.flight.model.Tag;
import by.vsu.flight.repo.TagRepository;
import by.vsu.flight.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends BaseServiceImpl<Tag, TagRepository>
        implements TagService {

    public TagServiceImpl(TagRepository repository) {
        super(repository);
    }

    @Override
    public Tag findByName(String name) {
        return getRepository().findByName(name);
    }
}
