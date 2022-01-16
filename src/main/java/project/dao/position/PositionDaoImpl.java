package project.dao.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.model.Position;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * {@inheritDoc}
 */
@Repository
@Transactional
public class PositionDaoImpl implements PositionDao {

    private final EntityManager em;

    @Autowired
    public PositionDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Position loadByName(String name) {
        CriteriaQuery<Position> criteria = buildCriteria("name", name);
        TypedQuery<Position> query = em.createQuery(criteria);
        Object result = query.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
        if (result != null) {
            return (Position) result;
        } else {
            return null;
        }
    }

    private CriteriaQuery<Position> buildCriteria(String attrName, String attrValue) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Position> criteria = builder.createQuery(Position.class);

        Root<Position> position = criteria.from(Position.class);
        criteria.where(builder.equal(position.get(attrName), attrValue));

        return criteria;
    }
}
