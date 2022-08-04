package by.vsu.auth.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Data
@Table(name = "flights")
@Entity
public class Flight extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "city_from_id")
    private City cityFrom;

    @ManyToOne
    @JoinColumn(name = "city_to_id")
    private City cityTo;

    private Date leaveDate;

    private Date arriveDate;

}
