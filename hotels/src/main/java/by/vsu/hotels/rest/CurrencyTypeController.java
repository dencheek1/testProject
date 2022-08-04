package by.vsu.hotels.rest;

import by.vsu.hotels.dto.CurrencyDto;
import by.vsu.hotels.model.CurrencyType;
import by.vsu.hotels.service.geo.CurrencyTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/currency-type")
public class CurrencyTypeController {

    private CurrencyTypeService currencyTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<CurrencyType>> getAll() {
        List<CurrencyType> currencyTypes;
        currencyTypes = currencyTypeService.getAll();
        log.info(currencyTypeService.findByName(currencyTypes.get(0).getName()).getName());
        return new ResponseEntity<>(currencyTypes, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<CurrencyType> getByName(String name) {
        CurrencyType currencyType = currencyTypeService.findByName(name);
        return new ResponseEntity<>(currencyType, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CurrencyType> addCurrencyType(CurrencyDto currencyDto){
        CurrencyType currencyType = currencyDto.toCurrencyType();
        currencyType.setId(null);
        CurrencyType saved = currencyTypeService.save(currencyType);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public void delete(String name) {
        CurrencyType currencyType = currencyTypeService.findByName(name);
        if (currencyType != null) {
            currencyTypeService.delete(currencyType);
        }
    }
}
