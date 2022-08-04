package by.vsu.tour.rest;

import by.vsu.tour.dto.*;
import by.vsu.tour.model.Country;
import by.vsu.tour.model.Tag;
import by.vsu.tour.model.Tour;
import by.vsu.tour.service.TourService;
import by.vsu.tour.service.geo.CountryService;
import by.vsu.tour.service.tag.TagService;
import by.vsu.tour.service.web.FlightDtoService;
import by.vsu.tour.service.web.HotelDtoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/tour")
public class TourController {

    private HotelDtoService hotelService;

    private FlightDtoService flightService;

    private TourService tourService;

    private CountryService countryService;

    private TagService tagService;

    @GetMapping("/name")
    public ResponseEntity<TourDto> getByName(@RequestParam String name) {
        Tour tour = tourService.findByName(name);

        if (tour == null) {
            log.info("Can't find tour with name: {}", name);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TourDto tourDto = mapToTourDto(tour);

        return new ResponseEntity<>(tourDto, HttpStatus.OK);
    }
    @GetMapping("/name/more-info")
    public ResponseEntity<TourDtoFullInfo> getByNameMoreInfo(@RequestParam String name) {
        Tour tour = tourService.findByName(name);

        if (tour == null) {
            log.info("Can't find tour with name: {}", name);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TourDtoFullInfo tourDto =new TourDtoFullInfo();
        tourDto.setName(name);
        tourDto.setFlight(flightService.findById(tour.getFlightId()));
        tourDto.setHotel(hotelService.findById(tour.getHotelId()));

        return new ResponseEntity<>(tourDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TourDto>> getAll() {

        List<TourDto> tourDtoList = tourService.getAll().stream().map(this::mapToTourDto).toList();

        return new ResponseEntity<>(tourDtoList, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<TourPostDto> updateTour(TourPostDto tourDto){
        Tour tour = tourService.findById(tourDto.getId());

        if(tour == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Country country = countryService.findByName(tourDto.getCountryName());
        if(country == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Tag> tags = tourDto.getTags().stream().map(tagService::findByName).toList();
        if(tags.contains(null)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        tour.setTags(tags);
        tour.setCountry(country);
        tour.setName(tourDto.getName());
        tour.setFlightId(tourDto.getFlightId());
        tour.setHotelId(tourDto.getHotelId());

        tour = tourService.save(tour);

        tourDto.setId(tour.getId());
        return new ResponseEntity<>(tourDto, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<TourPostDto> create(TourPostDto tourDto){
        Tour tour = tourService.findById(tourDto.getId());

        if(tour != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        tour = new Tour();

        Country country = countryService.findByName(tourDto.getCountryName());
        if(country == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Tag> tags = tourDto.getTags().stream().map(tagService::findByName).toList();
        if(tags.contains(null)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        tour.setTags(tags);
        tour.setCountry(country);
        tour.setName(tourDto.getName());
        tour.setFlightId(tourDto.getFlightId());
        tour.setHotelId(tourDto.getHotelId());

        tour = tourService.save(tour);

        tourDto.setId(tour.getId());
        return new ResponseEntity<>(tourDto, HttpStatus.OK);

    }

    @PostMapping("/delete")
    public void delete(@RequestParam String name){
        Tour tour = tourService.findByName(name);
        if(tour != null){
            tourService.delete(tour);
        }
    }

    private TourDto mapToTourDto(Tour tour) {

        TourDto tourDto = new TourDto();

        Set<String> tags = new HashSet<>();

        HotelDto hotelDto = hotelService.findById(tour.getHotelId());
        String hotelCityName = "";
        String hotelCountryName = "";

        if (hotelDto != null) {
            hotelCityName = hotelDto.getCityName();
            hotelCountryName = hotelDto.getCountryName();
            if (hotelDto.getTags() != null) {
                tags.addAll(hotelDto.getTags());
            }
        } else {
            log.warn("For a tour with the name: {} can't find hotel with id: {}", tour.getName(), tour.getHotelId());
        }

        FlightDto flightDto = flightService.findById(tour.getFlightId());
        String flightName = null;
        String flightCountryToName = "";

        if (flightDto != null) {
            flightName = flightDto.getName();
            flightCountryToName = flightDto.getCityTo().getCountryDto().getName();
            if (flightDto.getTags() != null) {
                tags.addAll(flightDto.getTags());
            }
        } else {
            log.warn("For a tour with the name: {} can't find flight with id: {}", tour.getName(), tour.getFlightId());
        }

        if (!flightCountryToName.equals(hotelCountryName)) {
            log.warn("For a tour with the name: {} flight country name and hotel country name doesn't match", tour.getName());
        }

        if(!hotelCountryName.equals(tour.getCountry().getName())){
            log.warn("For a tour with the name: {} flight country name doesn't match hotel country", tour.getName());
        }

        tourDto.setName(tour.getName());
        tourDto.setCity(hotelCityName);
        tourDto.setFlightName(flightName);
        tourDto.setTags(tags.stream().toList());
        tourDto.setCountryName(tour.getCountry().getName());
        return tourDto;
    }
}
