package by.vsu.hotels.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "hotels")
public class Hotel extends BaseEntity{

    private String name;

    private String description;

    private int placesAvailable;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "services_to_hotels",
        joinColumns = {@JoinColumn(name = "hotel_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "service_id", referencedColumnName = "id")})
    private List<HotelServiceModel> services;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "excursions_to_hotels",
            joinColumns = {@JoinColumn(name = "hotel_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "excursion_id", referencedColumnName = "id")})
    private List<Excursion> excursions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tags_to_hotels",
            joinColumns = {@JoinColumn(name = "hotel_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")})
    private List<Tag> tags;

}
