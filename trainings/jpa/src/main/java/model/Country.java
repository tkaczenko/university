package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by tkaczenko on 07.11.16.
 */
@Entity
@NamedQuery(name = "Country.getAll", query = "SELECT c FROM Country c")
public class Country implements Serializable {
    @Id
    @Column(nullable = false, columnDefinition = "bpchar(3)")
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String continent;

    @Column(name = "region", nullable = false)
    private String region;

    @Column
    private double surfaceArea;

    @Column(columnDefinition = "int2")
    private Short indepYear;

    @Column
    private int population;

    @Column
    private Double lifeExpectancy;

    @Column
    private Double gnp;

    @Column
    private Double gnpOld;

    @Column(nullable = false)
    private String localName;

    @Column(nullable = false)
    private String governmentForm;

    @Column
    private String headOfState;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "capital")
    private City capital;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", orphanRemoval = true)
    private Set<City> cities;

    @Column(nullable = false, columnDefinition = "bpchar(2)")
    private String code2;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", orphanRemoval = true)
    private Set<CountryLanguage> languages;

    public City getCapitalCity() {
        return capital;
    }

    public Country() {

    }

    public Country(String code, String name, String continent, String region, double surfaceArea, int population,
                   String localName, String governmentForm, String code2) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.surfaceArea = surfaceArea;
        this.population = population;
        this.localName = localName;
        this.governmentForm = governmentForm;
        this.code2 = code2;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public short getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(short indepYear) {
        this.indepYear = indepYear;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(double lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public double getGnp() {
        return gnp;
    }

    public void setGnp(double gnp) {
        this.gnp = gnp;
    }

    public double getGnpOld() {
        return gnpOld;
    }

    public void setGnpOld(double gnpOld) {
        this.gnpOld = gnpOld;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City city) {
        this.capital = city;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name=" + name +
                ", continent=" + continent +
                ", region=" + region +
                ", surfacearea=" + surfaceArea +
                ", indepyear=" + ((indepYear != null) ? indepYear.toString() : "null") +
                ", population=" + population +
                ", lifeexpectancy=" + ((lifeExpectancy != null) ? lifeExpectancy.toString() : "null") +
                ", gnp=" + ((gnp != null) ? gnp.toString() : "null") +
                ", gnpold=" + ((gnpOld != null) ? gnpOld.toString() : "null") +
                ", localname=" + localName +
                ", governmentfrom=" + governmentForm +
                ", headofstate=" + headOfState +
                ", capital=" + ((capital != null) ? capital.toString() : "null") +
                ", code2=" + code2 +
                '}';
    }
}
