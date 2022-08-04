package by.vsu.tour.service.tag;

import by.vsu.tour.model.Tag;
import by.vsu.tour.repo.TagRepository;
import by.vsu.tour.service.impl.BaseServiceImpl;
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
