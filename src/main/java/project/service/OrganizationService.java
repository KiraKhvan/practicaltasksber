package project.service;

import org.springframework.validation.annotation.Validated;
import project.view.OrganizationListView;
import project.view.OrganizationView;

import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OrganizationService {

    /**
     * Добавить новую организацию в БД
     */
    boolean save(
            String name,
            String fullName,
            Integer inn,
            Integer kpp,
            String address,
            String phone,
            Boolean isActive
    );

    /**
     * Изменить информацию об организации
     *
     * @return {@Organization}
     */
    boolean update(
            Long id,
            String name,
            String fullName,
            Integer inn,
            Integer kpp,
            String address,
            String phone,
            Boolean isActive
    );

    /**
     * Получить список организаций
     *
     * @return {@Organization}
     */
    List<OrganizationListView> getOrganizations(
            String name,
            String inn,
            Boolean isActive
    );

    /**
     * Получить организацию по идентификатору
     *
     * @return {@Organization}
     */
    OrganizationView getOrganization(Long id);
}
