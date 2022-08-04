package by.vsu.hotels.rest;

import by.vsu.hotels.dto.ExcursionDto;
import by.vsu.hotels.model.Excursion;
import by.vsu.hotels.service.excursion.ExcursionService;
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
@RequestMapping("/excursion")
public class ExcursionController {

    private ExcursionService excursionService;

    @GetMapping("/all")
    public ResponseEntity<List<ExcursionDto>> getAll(){
        List<ExcursionDto> excursionList = excursionService.getAll().stream().map(ExcursionDto::fromExcursion).collect(Collectors.toList());
        return new ResponseEntity<>(excursionList, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<ExcursionDto> getByName(String name){
        ExcursionDto excursion = ExcursionDto.fromExcursion(excursionService.findByName(name));
        return new ResponseEntity<>(excursion, HttpStatus.OK);
    }

    @GetMapping("/all/more-info")
    public ResponseEntity<List<Excursion>> getAllMoreInfo(){
        List<Excursion> excursions = excursionService.getAll();
        return new ResponseEntity<>(excursions, HttpStatus.OK);
    }

    @GetMapping("/name/more-info")
    public ResponseEntity<Excursion> getByNameMoreInfo(String name){
        Excursion excursion = excursionService.findByName(name);
        return new ResponseEntity<>(excursion, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Excursion> create(ExcursionDto excursionDto){
        Excursion excursion = excursionDto.toExcursion();
        if(excursionService.findByName(excursion.getName()) != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        excursion = excursionService.save(excursion);
        return new ResponseEntity<>(excursion, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Excursion> update(ExcursionDto excursionDto, Long id){
        Excursion excursion = excursionService.findById(id);
        if( excursion== null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        excursion.setName(excursionDto.getName());
        excursion.setDescription(excursionDto.getDescription());
        excursion = excursionService.save(excursion);
        return new ResponseEntity<>(excursion, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public void delete(String name){
        Excursion excursion = excursionService.findByName(name);
        excursionService.delete(excursion);
    }
}
