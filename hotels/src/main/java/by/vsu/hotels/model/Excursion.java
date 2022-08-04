package by.vsu.hotels.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "excursions")
public class Excursion extends BaseEntity{

    private String name;
    private String description;

}
