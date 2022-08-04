package by.vsu.flight.rest;

import by.vsu.flight.dto.FlightDto;
import by.vsu.flight.model.City;
import by.vsu.flight.model.Flight;
import by.vsu.flight.model.Plane;
import by.vsu.flight.service.CityService;
import by.vsu.flight.service.FlightService;
import by.vsu.flight.service.PlaneService;
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
@RequestMapping("/flights")
public class FlightsController {

    private FlightService flightService;
    private CityService cityService;

    private PlaneService planeService;

    @GetMapping("/all")
    public ResponseEntity<List<FlightDto>> getAll(){
        List<FlightDto> flights = flightService.getAll().stream().map(FlightDto::fromFlight).collect(Collectors.toList());
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/flight")
    public ResponseEntity<FlightDto> getFlightById(@RequestParam Long id){
        FlightDto flight = FlightDto.fromFlight(flightService.findById(id));
        return new ResponseEntity<>(flight,HttpStatus.OK);
    }

    @GetMapping("/flight/more-info")
    public ResponseEntity<Flight> getFlightByIdMoreInfo(@RequestParam Long id){
        Flight flight = flightService.findById(id);
        return new ResponseEntity<>(flight,HttpStatus.OK);
    }

    @GetMapping("/all/more-info")
    public ResponseEntity<List<Flight>> getAllMoreInfo(){
        List<Flight> flights = flightService.getAll();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/city-from")
    public ResponseEntity<List<Flight>> getAllByCityFrom(@RequestParam Long cityId){
        City city = cityService.findById(cityId);
        if(city == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Flight> flights = flightService.findByCityFrom(city);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/city-to")
    public ResponseEntity<List<Flight>> getAllByCityTo(@RequestParam Long cityId){
        City city = cityService.findById(cityId);
        if(city == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Flight> flights = flightService.findByCityTo(city);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Flight> addFlight(@RequestBody FlightDto flightDto){
        City cityFrom = cityService.findById(flightDto.getCityFrom().getId());
        City cityTo = cityService.findById(flightDto.getCityTo().getId());
        Plane plane = planeService.findByModel(flightDto.getPlane().getModel());
        if (cityTo == null || cityFrom == null || plane == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Flight flight = flightDto.toFlight();
        flight.setCityTo(cityTo);
        flight.setCityFrom(cityFrom);
        flight.setPlane(plane);
        log.info("Flight: {}", flight);
        flight = flightService.save(flight);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Flight> updateFlight(@RequestBody FlightDto flightDto, Long flightId){

        Flight flight = flightService.findById(flightId);

        if(flight == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        flight = flightService.save(flight);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public void delete(@RequestParam Long flightId){
        Flight flight = flightService.findById(flightId);
        flightService.delete(flight);
    }

}
