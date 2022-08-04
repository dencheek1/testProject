package by.vsu.hotels.dto;

import by.vsu.hotels.model.HotelServiceModel;
import lombok.Data;

@Data
public class HotelServiceDto {

    private String name;
    private String description;
    Long id;

    public HotelServiceModel toHotelServiceModel(){
        HotelServiceModel service = new HotelServiceModel();
        service.setName(name);
        service.setDescription(description);
        service.setId(id);
        return service;
    }

    public static HotelServiceDto fromHotelServiceModel(HotelServiceModel hotelServiceModel){
        if(hotelServiceModel == null){
            return null;
        }
        HotelServiceDto dto = new HotelServiceDto();
        dto.setName(hotelServiceModel.getName());
        dto.setDescription(hotelServiceModel.getDescription());
        dto.setId(hotelServiceModel.getId());
        return dto;
    }

}
