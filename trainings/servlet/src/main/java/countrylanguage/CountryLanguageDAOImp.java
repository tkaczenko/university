package countrylanguage;

import common.JpaDAOImp;

/**
 * Created by tkaczenko on 02.12.16.
 */
public class CountryLanguageDAOImp extends JpaDAOImp<CountryLanguage> implements CountryLanguageDAO {
    public CountryLanguageDAOImp() {
        super();
        setPersistentClass(CountryLanguage.class);
    }
}
