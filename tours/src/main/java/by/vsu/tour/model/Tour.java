package by.vsu.tour.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tours")
public class Tour extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tour_to_tag",
            joinColumns = {@JoinColumn(name = "tour_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")})

    private List<Tag> tags;

    @Column(name = "flight_id")
    private Long flightId;

    @Column(name = "hotel_id")
    private Long hotelId;

}
