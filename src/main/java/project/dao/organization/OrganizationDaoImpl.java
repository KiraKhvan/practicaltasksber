package project.dao.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import project.controller.exception.NotFoundException;
import project.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
@Transactional
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Organization loadById(Long id) {
        Organization organization = em.find(Organization.class, id);
        if (organization != null) {
            return organization;
        } else {
            throw new NotFoundException("There is no organization with such id");
        }

    }

    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    @Override
    public void update(Organization organization) {
        em.merge(organization);
    }

    @Override
    public List<Organization> getFilteredOrganizationList(String name, String inn, Boolean isActive) {
        String queryString = buildQueryStringForFilterOrganizationList(inn, isActive);

        Query query = em.createQuery(queryString);

        prepareQueryForFilterOrganizationList(query, name, inn, isActive);

        return query.getResultList();
    }

    protected String buildQueryStringForFilterOrganizationList(String inn, Boolean isActive) {
        StringBuilder builder = new StringBuilder("SELECT o FROM Organization o where o.name = :name");

        if (!StringUtils.isEmpty(inn)) {
            builder.append(" ");
            builder.append("and o.inn = :inn");
        }

        if (!StringUtils.isEmpty(isActive)) {
            builder.append(" ");
            builder.append("and o.isActive = :isActive");
        }

        return builder.toString();
    }

    protected void prepareQueryForFilterOrganizationList(
            Query query,
            String name,
            String inn,
            Boolean isActive
    ) {
        query.setParameter("name", name);

        if (!StringUtils.isEmpty(inn)) {
            query.setParameter("inn", inn);
        }

        if (!StringUtils.isEmpty(isActive)) {
            query.setParameter("isActive", isActive);
        }
    }
}
