package by.vsu.hotels.dto;

import by.vsu.hotels.model.City;
import by.vsu.hotels.model.Country;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityDto {

    private String name;
    private String description;
    private CountryDto countryDto;
    private long id;

    public City toCity(){
        Country country = countryDto.toCountry();
        City city = new City();
        city.setName(name);
        city.setDescription(description);
        city.setCountry(country);
        return city;
    }

    public static CityDto fromCity(City city){
        if (city == null) return null;
        CityDto cityDto = new CityDto();
        cityDto.setCountryDto(CountryDto.fromCountry(city.getCountry()));
        cityDto.setDescription(city.getDescription());
        cityDto.setName(city.getName());
        cityDto.setId(city.getId());
        return cityDto;
    }

}
