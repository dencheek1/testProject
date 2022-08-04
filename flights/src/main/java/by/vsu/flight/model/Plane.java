package by.vsu.flight.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "planes")
public class Plane extends BaseEntity {

    @Column(name = "plane_model")
    private String planeModel;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

}
