package project.service;

import org.springframework.validation.annotation.Validated;
import project.dto.CountryDto;

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
    List<CountryDto> getAllCountries();
}
