package project.dao.country;

import project.model.Country;

import java.util.List;

/**
 * DAO для работы с Country
 */
public interface CountryDao {

    /**
     * Получить Country по коду
     *
     * @param code
     * @return
     */
    Country loadByCode(String code);

    /**
     * Получить объекты Country
     *
     * @return
     */
    List<Country> getAllCountryList();
}
