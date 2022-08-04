package by.vsu.auth.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Base entity class. Contains all common entities fields.
 *
 * Date:24/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;


    /**
     * Date when entity was created.
     */
    @Column(name = "created")
    private Date created;

    /**
     * Last update date.
     */
    @Column(name = "updated")
    private Date updated;

}
