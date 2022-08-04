package by.vsu.hotels.service.geo.impl;

import by.vsu.hotels.model.Language;
import by.vsu.hotels.repo.LanguageRepository;
import by.vsu.hotels.service.BaseServiceImpl;
import by.vsu.hotels.service.geo.LanguageService;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl extends BaseServiceImpl<Language, LanguageRepository> implements LanguageService {

    public LanguageServiceImpl(LanguageRepository repository) {
        super(repository);
    }

    @Override
    public Language findByName(String name) {
        return getRepository().findByName(name);
    }
}
