package by.vsu.flight.service.impl;

import by.vsu.flight.model.City;
import by.vsu.flight.repo.CityRepository;
import by.vsu.flight.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl extends BaseServiceImpl<City, CityRepository>
        implements CityService {
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
