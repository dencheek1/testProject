package by.vsu.hotels.rest;

import by.vsu.hotels.dto.CountryDto;
import by.vsu.hotels.model.Country;
import by.vsu.hotels.model.CurrencyType;
import by.vsu.hotels.model.Language;
import by.vsu.hotels.service.geo.CountryService;
import by.vsu.hotels.service.geo.CurrencyTypeService;
import by.vsu.hotels.service.geo.LanguageService;
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
@RequestMapping("/country")
public class CountryController {

    private CountryService countryService;
    private CurrencyTypeService currencyTypeService;
    private LanguageService languageService;

    @GetMapping("/name")
    public ResponseEntity<CountryDto> getByName(String name){
        Country country = countryService.findByName(name);
        if(country == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CountryDto countryDto = CountryDto.fromCountry(country);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }

    @GetMapping("/name/more_info")
    public ResponseEntity<Country> getByNameMoreInfo(String name){
        Country country = countryService.findByName(name);
        if(country == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @GetMapping("/countries/more_info")
    public ResponseEntity<List<Country>>  getAllFullInfo(){
        List<Country> countries = countryService.getAll();
        return new ResponseEntity<>(countries,HttpStatus.OK);
    }

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDto>>  getAll(){
        List<CountryDto> countries = countryService.getAll().stream().map(CountryDto::fromCountry).collect(Collectors.toList());
        return new ResponseEntity<>(countries,HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Country> updateCountry(long id, CountryDto countryDto){

        Country country = countryService.findById(id);
        if(country==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Language> languages = null;
        List<String> languagesNames =  countryDto.getLanguages();
        if(languagesNames != null){
            languages = languagesNames.stream().map(s -> languageService.findByName(s)).collect(Collectors.toList());
            if (languages.contains(null)) {
                log.info("There is no such language in the database");
                return new ResponseEntity<>(country, HttpStatus.BAD_REQUEST);
            }
        }

        List<CurrencyType> currencyTypes = null;
        List<String> currencyTypesNames = countryDto.getCurrencyTypeList();
        if(currencyTypes != null){
            currencyTypes = currencyTypesNames.stream().map(s -> currencyTypeService.findByName(s)).collect(Collectors.toList());
            if (currencyTypes.contains(null)) {
                log.info("There is no such currency in the database");
                return new ResponseEntity<>(country, HttpStatus.BAD_REQUEST);
            }
        }

        country.setName(countryDto.getName());
        country.setLanguage(languages);
        country.setCurrencyType(currencyTypes);
        country.setDescription(countryDto.getDescription());
        country = countryService.save(country);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Country> createCountry(CountryDto countryDto){


        countryDto.getLanguages().forEach(log::info);
        Country country = countryService.findByName(countryDto.getName());
        if(country != null){
            return new ResponseEntity<>(country, HttpStatus.BAD_REQUEST);
        }

        List<Language> languages = null;
        List<String> languagesNames =  countryDto.getLanguages();
        if(languagesNames != null){
            languages = languagesNames.stream().map(s -> languageService.findByName(s)).collect(Collectors.toList());
            if (languages.contains(null)) {
                log.info("There is no such language in the database");
                return new ResponseEntity<>(country, HttpStatus.BAD_REQUEST);
            }
        }

        List<CurrencyType> currencyTypes = null;
        List<String> currencyTypesNames = countryDto.getCurrencyTypeList();
        if(currencyTypes != null){
            currencyTypes = currencyTypesNames.stream().map(s -> currencyTypeService.findByName(s)).collect(Collectors.toList());
            if (currencyTypes.contains(null)) {
                log.info("There is no such currency in the database");
                return new ResponseEntity<>(country, HttpStatus.BAD_REQUEST);
            }
        }

        country = countryDto.toCountry();
        country.setId(null);
        country.setLanguage(languages);
        country.setCurrencyType(currencyTypes);
        country = countryService.save(country);
        log.info("Saved country: {}",country);
        return new ResponseEntity<>(country, HttpStatus.OK);

    }

    @PostMapping("/delete")
    public void delete(Long id){
        Country country = countryService.findById(id);
        countryService.delete(country);
    }
}
