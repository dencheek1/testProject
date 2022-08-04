package by.vsu.flight.rest;

import by.vsu.flight.dto.CityDto;
import by.vsu.flight.model.City;
import by.vsu.flight.model.Country;
import by.vsu.flight.service.CityService;
import by.vsu.flight.service.CountryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/city")
public class CityController {

    private CityService cityService;
    private CountryService countryService;

    @PostMapping("/add")
    public ResponseEntity<CityDto> addCity(@RequestBody CityDto cityDto) {
        Country country = countryService.findByName(cityDto.getCountryDto().getName());
        if (country == null) {
            log.info("Can't find country with name: {}", cityDto.getCountryDto().getName());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        City city = cityDto.toCity();
        city.setCountry(country);
        city = cityService.save(city);
        return new ResponseEntity<>(CityDto.fromCity(city), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<CityDto> updateCity(@RequestBody CityDto cityDto) {
        City city = cityService.findById(cityDto.getId());
        if (city == null) {
            log.info("Can't find city with id: {}", cityDto.getCountryDto().getName());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Country country = countryService.findByName(cityDto.getCountryDto().getName());
        if (country == null) {
            log.info("Can't find country with name: {}", cityDto.getCountryDto().getName());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        city.setName(cityDto.getName());
        city.setDescription(cityDto.getDescription());
        city.setCountry(country);
        city = cityService.save(city);
        return new ResponseEntity<>(CityDto.fromCity(city), HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<CityDto> getById(long id) {
        CityDto cityDto = CityDto.fromCity(cityService.findById(id));
        return new ResponseEntity<>(cityDto, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<CityDto>> getByName(String name) {
        List<CityDto> cities = cityService.getByName(name).stream()
                .map(CityDto::fromCity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> getAll() {
        List<CityDto> cities = cityService.getAll().stream()
                .map(CityDto::fromCity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }


    @PostMapping("/remove")
    public void delete(Long cityId) {
        City city = cityService.findById(cityId);
        if (city != null) {
            cityService.delete(city);
        }
    }

}
