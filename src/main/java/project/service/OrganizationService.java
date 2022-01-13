package project.service;

import org.springframework.validation.annotation.Validated;
import project.dto.filter.organization.OrganizationFilter;
import project.dto.request.organization.AddOrganizationRequest;
import project.dto.request.organization.EditOrganizationRequest;
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
    boolean save(AddOrganizationRequest request);

    /**
     * Изменить информацию об организации
     *
     * @return {@Organization}
     */
    boolean update(EditOrganizationRequest request);

    /**
     * Получить список организаций
     *
     * @return {@Organization}
     */
    List<OrganizationListResponse> getOrganizations(OrganizationFilter filter);

    /**
     * Получить организацию по идентификатору
     *
     * @return {@Organization}
     */
    OrganizationResponse getOrganization(String id);
}
