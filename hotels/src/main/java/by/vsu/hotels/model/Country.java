package by.vsu.hotels.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static org.hibernate.annotations.FetchMode.SUBSELECT;

@Data
@Entity
@Table(name = "countries")
public class Country extends BaseEntity{

    private String name;

    private String description;

    @ManyToMany(fetch = EAGER)
    @Fetch(value = SUBSELECT)
    @JoinTable(name = "language_to_country",
            joinColumns = {@JoinColumn(name = "country_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "language_id", referencedColumnName = "id")})
    private List<Language> language;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "currency_to_country",
            joinColumns = {@JoinColumn(name = "country_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "currency_id", referencedColumnName = "id")})
    private List<CurrencyType> currencyType;


}
