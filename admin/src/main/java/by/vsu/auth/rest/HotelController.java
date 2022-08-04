package by.vsu.auth.rest;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@RestController
public class HotelController {

    private final RestTemplateBuilder restTemplate;

    @GetMapping("/hotel")
    public ResponseEntity<List<HotelDto>> hotels() {
        String url = "http://localhost:8076/hotel/hotels";
        RestTemplate template = restTemplate.build();
       return new ResponseEntity<>(List.of(template.getForObject(url, HotelDto[].class)), HttpStatus.OK);
    }

}
