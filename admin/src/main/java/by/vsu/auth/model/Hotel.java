package by.vsu.auth.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hotels")
public class Hotel extends BaseEntity{

    private String name;

    private String description;

    private String city;

}