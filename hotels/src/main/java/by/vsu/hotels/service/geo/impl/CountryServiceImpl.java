package by.vsu.hotels.service.geo.impl;

import by.vsu.hotels.model.Country;
import by.vsu.hotels.model.CurrencyType;
import by.vsu.hotels.model.Language;
import by.vsu.hotels.repo.CountryRepository;
import by.vsu.hotels.service.BaseServiceImpl;
import by.vsu.hotels.service.geo.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl extends BaseServiceImpl<Country, CountryRepository>
        implements CountryService {


    public CountryServiceImpl(CountryRepository repository) {
        super(repository);
    }

    @Override
    public Country update(Country country) {
        return null;
    }

    @Override
    public Country findByName(String name) {
        return getRepository().findByName(name);
    }

    @Override
    public List<Country> findByLanguage(Language language) {
        List<Country> countries = getRepository().findAll();
        countries.stream().filter(country -> country.getLanguage().contains(language)).collect(Collectors.toList());
        return countries;
    }

    @Override
    public List<Country> findByCurrencyType(CurrencyType currencyType) {
        List<Country> countries = getRepository().findAll();
        countries.stream().filter(country -> country.getCurrencyType().contains(currencyType)).collect(Collectors.toList());
        return countries;
    }
}
