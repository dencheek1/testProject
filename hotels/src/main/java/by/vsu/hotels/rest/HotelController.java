package by.vsu.hotels.rest;


import by.vsu.hotels.dto.HotelDto;
import by.vsu.hotels.model.*;
import by.vsu.hotels.service.excursion.ExcursionService;
import by.vsu.hotels.service.geo.CityService;
import by.vsu.hotels.service.hotel.HotelService;
import by.vsu.hotels.service.hotel.HotelServiceModelService;
import by.vsu.hotels.service.hotel.TagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/hotel")
public class HotelController {

    private HotelService service;
    private CityService cityService;

    private ExcursionService excursionService;

    private HotelServiceModelService hotelServiceModelService;

    private TagService tagService;

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelDto>> getAll() {
        List<HotelDto> hotels = HotelDto.fromHotel(service.getAll());
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/hotels/more-info")
    public ResponseEntity<List<Hotel>> getAllMoreInfo() {
        List<Hotel> hotels = service.getAll();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/hotel")
    public ResponseEntity<HotelDto> getById(@RequestParam Long id) {

        HotelDto hotel = HotelDto.fromHotel(service.findById(id));

        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<List<HotelDto>> getByCity(@RequestParam Long id) {
        City cityModer = cityService.findById(id);
        log.info("For request find city: {} by id: {} ", cityModer, id);
        List<HotelDto> hotels = HotelDto.fromHotel(service.findByCity(cityModer));
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/tagName")
    public ResponseEntity<List<HotelDto>> getBy(@RequestParam String tagName) {
        List<HotelDto> hotels = HotelDto.fromHotel(service.findByTagName(tagName));
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public void delete(Long id) {
        Hotel hotel = service.findById(id);
        service.delete(hotel);
    }

    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(HotelDto hotelDto, Long cityId) {

        Hotel hotel = new Hotel();

        List<Excursion> excursions = null;
        List<String> excursionNames = hotelDto.getExcursions();
        if(excursionNames != null){
            excursions = excursionNames.stream().map(excursionService::findByName).collect(Collectors.toList());
            if (excursions.contains(null)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        List<HotelServiceModel> hotelServices  = null;
        List<String> hotelServicesNames = hotelDto.getServices();
        if (hotelServicesNames != null) {
            hotelServices =hotelServicesNames.stream().map(hotelServiceModelService::findByName).collect(Collectors.toList());
            if (hotelServices.contains(null)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        City city = cityService.findById(cityId);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Tag> tags = null;
        List<String> tagNames = hotelDto.getTags();
        if (tagNames != null) {
            tags = tagNames.stream().map(tagService::findByName).collect(Collectors.toList());
            if (tags.contains(null)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }


        hotel.setDescription(hotelDto.getDescription());
        hotel.setName(hotelDto.getName());
        hotel.setCity(city);
        hotel.setServices(hotelServices);
        hotel.setExcursions(excursions);
        hotel.setTags(tags);
        hotel = service.save(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Hotel> update(HotelDto hotelDto, Long cityId, Long hotelId) {

        Hotel hotel = service.findById(hotelId);
        if (hotel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Excursion> excursions = null;
        List<String> excursionNames = hotelDto.getExcursions();
        if(excursionNames != null){
            excursions = excursionNames.stream().map(excursionService::findByName).collect(Collectors.toList());
            if (excursions.contains(null)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        List<HotelServiceModel> hotelServices  = null;
        List<String> hotelServicesNames = hotelDto.getServices();
        if (hotelServicesNames != null) {
        hotelServices =hotelServicesNames.stream().map(hotelServiceModelService::findByName).collect(Collectors.toList());
            if (hotelServices.contains(null)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        City city = cityService.findById(cityId);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Tag> tags = null;
        List<String> tagNames = hotelDto.getTags();
        if (tagNames != null) {
            tags = tagNames.stream().map(tagService::findByName).collect(Collectors.toList());
            if (tags.contains(null)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        hotel.setDescription(hotelDto.getDescription());
        hotel.setName(hotelDto.getName());
        hotel.setCity(city);
        hotel.setServices(hotelServices);
        hotel.setExcursions(excursions);
        hotel.setTags(tags);
        hotel = service.save(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

}
