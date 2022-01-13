package project.service;

import org.springframework.validation.annotation.Validated;
import project.dto.filter.office.OfficeFilter;
import project.dto.request.office.AddOfficeRequest;
import project.dto.request.office.EditOfficeRequest;
import project.dto.response.office.OfficeListResponse;
import project.dto.response.office.OfficeResponse;

import java.util.List;

/**
 * Сервис для работы с офисами
 */
@Validated
public interface OfficeService {

    /**
     * Добавить новый офис в БД
     */
    boolean save(AddOfficeRequest request);

    /**
     * Изменить информацию об офисе
     */
    boolean update(EditOfficeRequest request);

    /**
     * Получить фильтрованный список офисов
     *
     * @return {@Office}
     */
    List<OfficeListResponse> getOffices(OfficeFilter filter);

    /**
     * Получить офис по идентификатору
     *
     * @return {@Office}
     */
    OfficeResponse getOffice(Long id);
}
