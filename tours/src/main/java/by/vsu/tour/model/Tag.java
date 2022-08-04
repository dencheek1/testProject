package by.vsu.tour.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tags")
public class Tag extends BaseEntity {

    private String name;

}
