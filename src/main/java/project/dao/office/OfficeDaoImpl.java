package project.dao.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import project.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
@Transactional
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Office loadById(Long id) {
        return em.find(Office.class, id);
    }

    @Override
    public Office loadByName(String name) {
        Object result = em.createQuery("SELECT o FROM Office o where o.name = :name")
                .setParameter("name", name)
                .getSingleResult();
        if (result != null) {
            return (Office) result;
        } else {
            return null;
        }
    }

    @Override
    public void save(Office office) {
        em.persist(office);
    }

    @Override
    public void update(Office office) {
        em.merge(office);
    }

    @Override
    public List<Office> getFilteredOfficeList(
            Long orgId,
            String name,
            String phone,
            Boolean isActive
    ) {
        String queryString = buildQueryStringForFilterOfficeList(name, phone, isActive);

        Query query = em.createQuery(queryString);

        prepareQueryForFilterOfficeList(query, orgId, name, phone, isActive);

        return query.getResultList();
    }

    protected String buildQueryStringForFilterOfficeList(
            String name,
            String phone,
            Boolean isActive
    ) {
        StringBuilder builder = new StringBuilder("SELECT o FROM Office o where o.organization.id = :orgId");

        if (!StringUtils.isEmpty(name)) {
            builder.append(" ");
            builder.append("and o.name = :name");
        }

        if (!StringUtils.isEmpty(phone)) {
            builder.append(" ");
            builder.append("and o.phone = :phone");
        }

        if (!StringUtils.isEmpty(isActive)) {
            builder.append(" ");
            builder.append("and o.isActive = :isActive");
        }

        return builder.toString();
    }

    protected void prepareQueryForFilterOfficeList(
            Query query,
            Long orgId,
            String name,
            String phone,
            Boolean isActive
    ) {
        query.setParameter("orgId", orgId);

        if (!StringUtils.isEmpty(name)) {
            query.setParameter("name", name);
        }

        if (!StringUtils.isEmpty(phone)) {
            query.setParameter("phone", phone);
        }

        if (!StringUtils.isEmpty(isActive)) {
            query.setParameter("isActive", isActive);
        }
    }
}
