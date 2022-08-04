package by.vsu.flight.dto;

import by.vsu.flight.model.City;
import by.vsu.flight.model.Country;
import lombok.Getter;
import lombok.Setter;

/**
 * Basic city data transfer object class.
 * Date:22/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@Setter
@Getter
public class CityDto {

    /**
     * City name.
     */
    private String name;

    /**
     * City description.
     */
    private String description;

    /**
     * Country data transfer object.
     * Contains information about the country of the city.
     */
    private CountryDto countryDto;

    /**
     * City id.
     */
    private long id;

    /**
     * Convert the current city data transfer object to a city object.
     * @return City.
     */
    public City toCity() {
        Country country = countryDto.toCountry();
        City city = new City();
        city.setName(name);
        city.setDescription(description);
        city.setCountry(country);
        return city;
    }

    /**
     * Create a city DTO object from a city object.
     *
     * @param city
     * @return CityDto.
     */
    public static CityDto fromCity(City city) {
        if (city == null) {
            return null;
        }
        CityDto cityDto = new CityDto();
        cityDto.setCountryDto(CountryDto.fromCountry(city.getCountry()));
        cityDto.setDescription(city.getDescription());
        cityDto.setName(city.getName());
        cityDto.setId(city.getId());
        return cityDto;
    }

}
