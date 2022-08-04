package by.vsu.hotels.service.geo.impl;

import by.vsu.hotels.model.City;
import by.vsu.hotels.repo.CityRepository;
import by.vsu.hotels.service.BaseServiceImpl;
import by.vsu.hotels.service.geo.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl extends BaseServiceImpl<City, CityRepository> implements CityService {
    public CityServiceImpl(CityRepository repository) {
        super(repository);
    }

    @Override
    public List<City> getByName(String name) {
        return getRepository().findByName(name);
    }

    @Override
    public List<City> getByCountryName(String country) {
        return getRepository().findByCountryName(country);
    }
}
