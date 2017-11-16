package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tkaczenko on 07.11.16.
 */
@Entity
@NamedQuery(name = "CountryLanguage.getAll", query = "SELECT c FROM CountryLanguage c")
public class CountryLanguage implements Serializable {
    @EmbeddedId
    private LanguageID id;

    @Column
    private boolean isOfficial;

    @Column
    private double percentage;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "countrycode", referencedColumnName = "code")
    private Country country;

    public CountryLanguage() {

    }

    public CountryLanguage(LanguageID languageID, boolean isOfficial, double percentage) {
        this.id = languageID;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }

    public String getCountryCode() {
        return id.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.id.countryCode = countryCode;
    }

    public String getLanguage() {
        return this.id.language;
    }

    public void setLanguage(String language) {
        this.id.language = language;
    }

    public boolean isOfficial() {
        return isOfficial;
    }

    public void setOfficial(boolean official) {
        isOfficial = official;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "CountryLanguage{" +
                "countryCode='" + id.countryCode + '\'' +
                ", language=" + id.language +
                ", isOfficial=" + isOfficial +
                ", percentage=" + percentage +
                '}';
    }

    @Embeddable
    private static class LanguageID implements Serializable {
        @Column(nullable = false)
        String countryCode;

        @Column(nullable = false)
        String language;

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }
}
