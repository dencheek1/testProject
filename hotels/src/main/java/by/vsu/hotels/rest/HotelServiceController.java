package by.vsu.hotels.rest;

import by.vsu.hotels.dto.HotelServiceDto;
import by.vsu.hotels.dto.LanguageDto;
import by.vsu.hotels.model.HotelServiceModel;
import by.vsu.hotels.model.Language;
import by.vsu.hotels.service.hotel.HotelServiceModelService;
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
@RequestMapping("/hotel-service")
public class HotelServiceController {

    private HotelServiceModelService service;

    @GetMapping("/all")
    public ResponseEntity<List<HotelServiceDto>> getAll(){
        List<HotelServiceModel> languages = service.getAll();
        List<HotelServiceDto> dtoList = languages.stream().map(HotelServiceDto::fromHotelServiceModel).collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<HotelServiceDto> getByName(Long id){
        HotelServiceDto hotelServiceModel = HotelServiceDto.fromHotelServiceModel(service.findById(id));
        if(hotelServiceModel == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(hotelServiceModel, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HotelServiceDto> addLanguage(HotelServiceDto hotelServiceDto){
        HotelServiceModel hotelServiceModel = hotelServiceDto.toHotelServiceModel();
        hotelServiceModel.setId(null);
        hotelServiceModel = service.save(hotelServiceModel);
        return new ResponseEntity<>(HotelServiceDto.fromHotelServiceModel(hotelServiceModel),HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delete(Long id){
        HotelServiceModel hotelServiceModel = service.findById(id);
        if (hotelServiceModel == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.delete(hotelServiceModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
