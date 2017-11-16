package city;

import country.Country;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tkaczenko on 02.12.16.
 */
@Entity
@NamedQuery(name = "City.getAll", query = "SELECT c FROM City c")
public final class City implements Serializable {
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

    private City(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.country = builder.country;
        this.district = builder.district;
        this.population = builder.population;
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

    public static class Builder {
        private final int id;
        private final String name;
        private final String district;
        private final int population;

        private Country country;

        public Builder(Integer id, String name, String district, Integer population) {
            if (id == null || id == -1 || name == null || district == null || population == null || population == -1) {
                throw new IllegalArgumentException("Don't put all not nullable fields");
            }
            this.id = id;
            this.name = name;
            this.district = district;
            this.population = population;
        }

        public Builder withCountry(Country country) {
            this.country = country;
            return this;
        }

        public City build() {
            return new City(this);
        }

    }
}
