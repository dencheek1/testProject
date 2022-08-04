package by.vsu.tour.service.geo.impl;

import by.vsu.tour.model.Country;

import by.vsu.tour.repo.CountryRepository;
import by.vsu.tour.service.impl.BaseServiceImpl;
import by.vsu.tour.service.geo.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl
        extends BaseServiceImpl<Country, CountryRepository>
        implements CountryService {


    public CountryServiceImpl(CountryRepository repository) {
        super(repository);
    }

    @Override
    public Country findByName(String name) {
        return getRepository().findByName(name);
    }

}
