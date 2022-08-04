package by.vsu.auth.rest;

import by.vsu.auth.model.City;
import by.vsu.auth.service.geo.CityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/city")
public class CityController {

    private CityService cityService;

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
    public void delete(CityDto cityDto) {
        City city = cityDto.toCity();
        cityService.delete(city);
    }

}
