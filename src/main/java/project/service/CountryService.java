package project.service;

import org.springframework.validation.annotation.Validated;
import project.view.CountryView;

import java.util.List;

/**
 * Сервис
 */
@Validated
public interface CountryService {

    /**
     * Получить список стран
     *
     * @return {@Country}
     */
    List<CountryView> getAllCountries();
}
