package project.service;

import org.springframework.validation.annotation.Validated;
import project.dto.response.organization.OrganizationListResponse;
import project.dto.response.organization.OrganizationResponse;

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
            String inn,
            String kpp,
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
            String id,
            String name,
            String fullName,
            String inn,
            String kpp,
            String address,
            String phone,
            Boolean isActive
    );

    /**
     * Получить список организаций
     *
     * @return {@Organization}
     */
    List<OrganizationListResponse> getOrganizations(
            String name,
            String inn,
            Boolean isActive
    );

    /**
     * Получить организацию по идентификатору
     *
     * @return {@Organization}
     */
    OrganizationResponse getOrganization(String id);
}
