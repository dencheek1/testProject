package by.vsu.tour.service.web.impl;

import by.vsu.tour.dto.FlightDto;
import by.vsu.tour.dto.HotelDto;
import by.vsu.tour.service.web.FlightDtoService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightDtoServiceImpl implements FlightDtoService {

    private final RestTemplateBuilder templateBuilder;

    @Setter
    @Value("${by.vsu.flight-service.url}")
    private String baseUrl;

    @Override
    public FlightDto findById(Long id) {
        String requestUrl = baseUrl + "/flight?id={id}";
        RestTemplate template = templateBuilder.build();
        ResponseEntity<FlightDto> hotelResponse = template.getForEntity(requestUrl,FlightDto.class, id);
        if(hotelResponse.getStatusCode().equals(HttpStatus.OK)){
            return hotelResponse.getBody();
        }
        return null;
    }

    @Override
    public List<FlightDto> getAll() {
        return null;
    }

    @Override
    public List<FlightDto> findByCityFrom(Long id) {
        return null;
    }

    @Override
    public List<FlightDto> findByCityTo(Long id) {
        return null;
    }
}
