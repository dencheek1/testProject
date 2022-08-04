package by.vsu.flight.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

    private String name;

    private String description;

}
