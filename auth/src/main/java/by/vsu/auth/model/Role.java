package by.vsu.auth.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Role entity class.
 *
 * Date:24/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@Entity
@Data
@Table(name = "roles")
public class Role extends BaseEntity {

    /**
     * Role name.
     */
    @Column(name = "name")
    private String name;

}
