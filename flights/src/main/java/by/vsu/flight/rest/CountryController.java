package by.vsu.flight.rest;

import by.vsu.flight.dto.CountryDto;
import by.vsu.flight.model.Country;
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
@RequestMapping("/country")
public class CountryController {

    private CountryService countryService;

    @GetMapping("/all")
    public ResponseEntity<List<CountryDto>> getAll() {
        List<CountryDto> countryDtoList = countryService.getAll().stream().map(CountryDto::fromCountry).collect(Collectors.toList());
        return new ResponseEntity<>(countryDtoList, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<CountryDto> getByName(String name) {
        CountryDto countryDto = CountryDto.fromCountry(countryService.findByName(name));
        log.info("Country dto: {}", name);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CountryDto> addCity(@RequestBody CountryDto countryDto) {
        Country country = countryService.findByName(countryDto.getName());
        if (country != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        country = countryDto.toCountry();
        country = countryService.save(country);
        return new ResponseEntity<>(CountryDto.fromCountry(country),HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<CountryDto> remove(String countryName){
        Country country = countryService.findByName(countryName);
        if(country != null){
            countryService.delete(country);
            return new ResponseEntity<>(CountryDto.fromCountry(country),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
