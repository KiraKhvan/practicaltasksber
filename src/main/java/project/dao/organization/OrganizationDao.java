package project.dao.organization;

import project.model.Organization;

import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return
     */
    Organization loadById(Long id);

    /**
     * Сохранить новую Organization
     *
     * @param organization
     */
    void save(Organization organization);

    /**
     * Сохранить Organization
     *
     * @param organization
     */
    void update(Organization organization);

    /**
     * Получить отфильтрованные объекты Organization
     *
     * @return
     */
    List<Organization> getFilteredOrganizationList(String name, String inn, Boolean isActive);
}
