package project.dao.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
@Transactional
public class CountryDaoImpl implements CountryDao {

    private final EntityManager em;

    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Country loadByCode(String code) {
        CriteriaQuery<Country> criteria = buildCriteria("code", code);
        TypedQuery<Country> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public List<Country> getAllCountryList() {
        TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c", Country.class);
        return query.getResultList();
    }

    private CriteriaQuery<Country> buildCriteria(String attrName, String attrValue) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteria = builder.createQuery(Country.class);

        Root<Country> entity = criteria.from(Country.class);
        criteria.where(builder.equal(entity.get(attrName), attrValue));

        return criteria;
    }
}
