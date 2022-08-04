package by.vsu.hotels.service.geo;

import by.vsu.hotels.model.City;
import by.vsu.hotels.service.BaseService;

import java.util.List;

public interface CityService extends BaseService<City> {

    List<City> getByCountryName(String country);
    List<City> getByName(String name);
}
