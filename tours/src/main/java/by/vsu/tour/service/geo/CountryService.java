package by.vsu.tour.service.geo;

import by.vsu.tour.model.Country;
import by.vsu.tour.service.BaseService;

public interface CountryService extends BaseService<Country> {

    Country findByName(String name);

}
