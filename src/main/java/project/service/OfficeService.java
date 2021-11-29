package project.service;

import org.springframework.validation.annotation.Validated;
import project.view.OfficeListView;
import project.view.OfficeView;

import java.util.List;

/**
 * Сервис для работы с офисами
 */
@Validated
public interface OfficeService {

    /**
     * Добавить новый офис в БД
     */
    boolean save(
            Long orgId,
            String name,
            String address,
            String phone,
            Boolean isActive
    );

    /**
     * Изменить информацию об офисе
     */
    boolean update(
            Long officeId,
            String name,
            String address,
            String phone,
            Boolean isActive
    );

    /**
     * Получить фильтрованный список офисов
     *
     * @return {@Office}
     */
    List<OfficeListView> getOffices(
            Long orgId,
            String name,
            String phone,
            Boolean isActive
    );

    /**
     * Получить офис по идентификатору
     *
     * @return {@Office}
     */
    OfficeView getOffice(Long id);
}
