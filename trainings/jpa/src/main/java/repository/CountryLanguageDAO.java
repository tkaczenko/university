package repository;

import model.CountryLanguage;

import javax.persistence.EntityManager;

/**
 * Created by tkaczenko on 09.11.16.
 */
public class CountryLanguageDAO extends AbstractJpaDAO<CountryLanguage> {
    public CountryLanguageDAO(EntityManager entityManager) {
        super(entityManager);
        setClazz(CountryLanguage.class);
    }
}