package by.vsu.tour.service.web.impl;

import by.vsu.tour.dto.HotelDto;
import by.vsu.tour.service.web.HotelDtoService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelDtoServiceImpl implements HotelDtoService {

    private final RestTemplateBuilder restTemplateBuilder;

    @Setter
    @Value("${by.vsu.hotel-service.url}")
    private String baseUrl;

    @Override
    public HotelDto findById(Long id) {
        String requestUrl = baseUrl + "/hotel?id={id}";
        RestTemplate template = restTemplateBuilder.build();
        ResponseEntity<HotelDto> hotelResponse = template.getForEntity(requestUrl,HotelDto.class, id);
        if(hotelResponse.getStatusCode().equals(HttpStatus.OK)){
            return hotelResponse.getBody();
        }
        return null;
    }
    @Override
    public List<HotelDto> getAll() {
        String requestUrl = baseUrl + "/hotels";
        RestTemplate template = restTemplateBuilder.build();
        ResponseEntity<HotelDto[]> hotelsResponse = template.getForEntity(requestUrl, HotelDto[].class);
        if(hotelsResponse.getStatusCode().equals(HttpStatus.OK)){
            return List.of(hotelsResponse.getBody());
        }
        return null;
    }
}
