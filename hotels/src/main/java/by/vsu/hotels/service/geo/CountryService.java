package by.vsu.hotels.service.geo;

import by.vsu.hotels.model.Country;
import by.vsu.hotels.model.CurrencyType;
import by.vsu.hotels.model.Language;
import by.vsu.hotels.service.BaseService;

import java.util.List;

public interface CountryService extends BaseService<Country> {

    Country update(Country country);

    Country findByName(String name);

    List<Country> findByLanguage(Language language);

    List<Country> findByCurrencyType(CurrencyType currencyType);

}
