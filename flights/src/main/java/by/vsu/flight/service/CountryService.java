package by.vsu.flight.service;

import by.vsu.flight.model.Country;

public interface CountryService extends BaseService<Country> {

    Country findByName(String name);

}
