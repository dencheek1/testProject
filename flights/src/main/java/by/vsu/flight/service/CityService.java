package by.vsu.flight.service;

import by.vsu.flight.model.City;

import java.util.List;

public interface CityService extends BaseService<City> {

    List<City> getByCountryName(String country);
    List<City> getByName(String name);
}
