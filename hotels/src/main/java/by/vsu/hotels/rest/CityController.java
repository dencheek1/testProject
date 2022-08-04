package by.vsu.hotels.rest;

import by.vsu.hotels.dto.CityCreateDto;
import by.vsu.hotels.dto.CityDto;
import by.vsu.hotels.model.City;
import by.vsu.hotels.model.Country;
import by.vsu.hotels.service.geo.CityService;
import by.vsu.hotels.service.geo.CountryService;
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
    public ResponseEntity<CityDto> addTag( @RequestBody CityCreateDto cityDto) {
        Country country = countryService.findByName(cityDto.getCountryName());
        if(country == null){
            log.info("Cant find country with name: {}", cityDto.getCountryName());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        City city = new City();
        city.setName(cityDto.getName());
        city.setCountry(country);
        city.setDescription(cityDto.getDescription());
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
    public ResponseEntity<Object> delete(Long id) {
        City city =  cityService.findById(id);
        if(city == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cityService.delete(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
