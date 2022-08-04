package by.vsu.flight.service.impl;

import by.vsu.flight.model.Country;

import by.vsu.flight.repo.CountryRepository;
import by.vsu.flight.service.CountryService;
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
