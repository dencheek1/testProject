package by.vsu.hotels.dto;

import by.vsu.hotels.model.Country;
import by.vsu.hotels.model.CurrencyType;
import by.vsu.hotels.model.Language;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDto {
    private String name;
    private List<String> languages;
    private List<String> currencyTypeList;
    private String description;

    public Country toCountry() {
        Country country = new Country();
        country.setName(name);
        country.setDescription(description);
        country.setLanguage(languages.stream()
                .map(l -> {
                    Language language = new Language();
                    language.setName(l);
                    return language;
                })
                .collect(Collectors.toList()));
        country.setCurrencyType(currencyTypeList.stream()
                .map(c -> {
                    CurrencyType currencyType = new CurrencyType();
                    currencyType.setName(c);
                    return currencyType;
                })
                .collect(Collectors.toList()));
        return country;
    }

    public static CountryDto fromCountry(Country country) {
        if(country == null) return null;
        CountryDto countryDto = new CountryDto();
        countryDto.setDescription(country.getDescription());
        countryDto.setName(country.getName());
        countryDto.setLanguages(country.getLanguage().stream()
                .map(Language::getName)
                .collect(Collectors.toList()));
        countryDto.setCurrencyTypeList(country.getCurrencyType().stream()
                .map(CurrencyType::getName)
                .collect(Collectors.toList()));
        return countryDto;
    }
}
