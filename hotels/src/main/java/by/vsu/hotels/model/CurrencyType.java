package by.vsu.hotels.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "currency_types")
public class CurrencyType extends BaseEntity{

    private String name;

}
