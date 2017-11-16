package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tkaczenko on 07.11.16.
 */
@Entity
@NamedQuery(name = "City.getAll", query = "SELECT c FROM City c")
public class City implements Serializable {
    //// FIXME: 24.11.16 Autoincrement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "countrycode", columnDefinition = "bpchar(3)", nullable = false)
    private Country country;

    @Column(nullable = false)
    private String district;

    @Column
    private int population;

    public City() {

    }

    public City(int id, String name, Country country, String district, int population) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", countryCode=" + country.getCode() +
                ", district=" + district +
                ", population=" + population +
                '}';
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
