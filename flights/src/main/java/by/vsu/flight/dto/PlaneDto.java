package by.vsu.flight.dto;

import by.vsu.flight.model.Plane;
import lombok.Data;

@Data
public class PlaneDto {
    private String name;
    private String model;
    private Integer capacity;

    public Plane toPlane(){
        Plane plane = new Plane();
        plane.setPlaneModel(model);
        plane.setName(name);
        plane.setCapacity(capacity);
        return plane;
    }

    public static PlaneDto fromPlane(Plane plane){
        if(plane == null) return null;
        PlaneDto planeDto = new PlaneDto();
        planeDto.setName(plane.getName());
        planeDto.setModel(plane.getPlaneModel());
        planeDto.setCapacity(plane.getCapacity());
        return planeDto;
    }
}
