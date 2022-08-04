package by.vsu.hotels.dto;

import by.vsu.hotels.model.Excursion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcursionDto {

    private String name;
    private String description;

    public Excursion toExcursion(){
        Excursion excursion = new Excursion();
        excursion.setName(name);
        excursion.setDescription(description);
        return excursion;
    }

    public static ExcursionDto fromExcursion(Excursion excursion){
        ExcursionDto excursionDto = new ExcursionDto();
        excursionDto.setDescription(excursion.getDescription());
        excursionDto.setName(excursion.getName());
        return excursionDto;
    }

}
