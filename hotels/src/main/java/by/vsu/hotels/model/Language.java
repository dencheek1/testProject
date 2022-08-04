package by.vsu.hotels.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "languages")
public class Language extends BaseEntity{

    private String name;

}
