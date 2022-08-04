package by.vsu.tour.dto;

import by.vsu.tour.model.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDto {
    /**
     * Country name.
     */
    private String name;
    /**
     * Country description.
     */
    private String description;

    /**
     * Convert the current country DTO to a country object.
     * @return Country.
     */
    public Country toCountry() {
        Country country = new Country();
        country.setName(name);
        country.setDescription(description);
        return country;
    }

    /**
     * Create a country data transfer object from a country object.
     * @param country
     * @return country DTO.
     */
    public static CountryDto fromCountry(Country country) {
        if (country == null) {
            return null;
        }
        CountryDto countryDto = new CountryDto();
        countryDto.setDescription(country.getDescription());
        countryDto.setName(country.getName());
        return countryDto;
    }
}
