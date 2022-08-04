package by.vsu.hotels.service.geo;

import by.vsu.hotels.model.Language;
import by.vsu.hotels.service.BaseService;


public interface LanguageService extends BaseService<Language> {

    Language findByName(String name);
}
