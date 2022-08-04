package by.vsu.tour.dto;

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
     * Contains information about a country of the city.
     */
    private CountryDto countryDto;

    /**
     * City id.
     */
    private long id;

}
