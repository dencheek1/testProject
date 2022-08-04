package by.vsu.flight.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Data
@Table(name = "flights")
@Entity
public class Flight extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_from_id")
    private City cityFrom;

    @ManyToOne
    @JoinColumn(name = "city_to_id")
    private City cityTo;

    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;

    private Date leaveDate;

    private Date arriveDate;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "tag_to_flight",
            joinColumns = {@JoinColumn(
                    name = "tag_id",
                    referencedColumnName = "id")
            },
            inverseJoinColumns = {@JoinColumn(
                    name = "flight_id",
                    referencedColumnName = "id")
    }
    )
    private List<Tag> tags;

}
