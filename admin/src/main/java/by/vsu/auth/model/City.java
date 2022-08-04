package by.vsu.auth.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}
