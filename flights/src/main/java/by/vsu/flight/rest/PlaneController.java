package by.vsu.flight.rest;

import by.vsu.flight.dto.PlaneDto;
import by.vsu.flight.model.Plane;
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
@RequestMapping("/plane")
public class PlaneController {

    private PlaneService planeService;

    @GetMapping("/all")
    public ResponseEntity<List<PlaneDto>> getAll(){
        List<PlaneDto> planes = planeService.getAll().stream().map(PlaneDto::fromPlane).collect(Collectors.toList());
        return new ResponseEntity<>(planes, HttpStatus.OK);
    }

    @GetMapping("/min-capacity/{capacity}")
    public ResponseEntity<List<PlaneDto>> getWithMinCapacity(@RequestParam int capacity){
        List<PlaneDto> planes = planeService.findWithMinCapacity(capacity).stream().map(PlaneDto::fromPlane).collect(Collectors.toList());
        return new ResponseEntity<>(planes, HttpStatus.OK);
    }

    @GetMapping("/plane/more-info/{model}")
    public ResponseEntity<Plane> getMoreInfoForPlaneWithModel(@RequestParam String model){
        Plane plane = planeService.findByModel(model);
        return new ResponseEntity<>(plane, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Plane> addPlane(@RequestBody PlaneDto planeDto){
        Plane plane = planeService.findByModel(planeDto.getModel());
        if(plane != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        plane = planeDto.toPlane();
        plane = planeService.save(plane);
        return new ResponseEntity<>(plane, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public void delete(@RequestParam long id){
        Plane plane = planeService.findById(id);
        if(plane != null) {
            planeService.delete(plane);
        }
    }
}
