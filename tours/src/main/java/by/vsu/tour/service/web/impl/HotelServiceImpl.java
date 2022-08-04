package by.vsu.tour.service.web.impl;

import by.vsu.tour.dto.HotelDto;
import by.vsu.tour.service.web.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final RestTemplateBuilder restTemplateBuilder;
    @Value("${by.vsu.hotel-service.url}")
    private String baseUrl;

    @Override
    public HotelDto findById(Long id) {
        String requestUrl = baseUrl + "/hotel/{id}";
        RestTemplate template = restTemplateBuilder.build();
        HotelDto hotel = template.getForObject(requestUrl,HotelDto.class, id);
        return hotel;
    }

    @Override
    public List<HotelDto> getAll() {
        String requestUrl = baseUrl + "/hotels";
        RestTemplate template = restTemplateBuilder.build();
        List<HotelDto> hotels = List.of(template.getForObject(requestUrl, HotelDto[].class));
        //
        return hotels;
    }

}
