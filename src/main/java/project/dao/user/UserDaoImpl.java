package project.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import project.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User loadById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public List<User> getFilteredUserList(
            Long officeId,
            String firstName,
            String lastName,
            String middleName,
            String positionName,
            String docCode,
            String citizenshipCode
    ) {
        CriteriaQuery<User> criteria = buildCriteria(
                officeId,
                firstName,
                lastName,
                middleName,
                positionName,
                docCode,
                citizenshipCode
        );
        TypedQuery<User> query = em.createQuery(criteria);
        return query.getResultList();
    }

    private CriteriaQuery<User> buildCriteria(
            Long officeId,
            String firstName,
            String lastName,
            String middleName,
            String positionName,
            String docCode,
            String citizenshipCode
    ) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> entity = criteria.from(User.class);
        List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(entity.get("office").get("id"), officeId));

        if (!StringUtils.isEmpty(firstName)) {
            predicates.add(builder.equal(entity.get("firstName"), firstName));
        }

        if (!StringUtils.isEmpty(lastName)) {
            predicates.add(builder.equal(entity.get("lastName"), lastName));
        }

        if (!StringUtils.isEmpty(middleName)) {
            predicates.add(builder.equal(entity.get("middleName"), middleName));
        }

        if (!StringUtils.isEmpty(positionName)) {
            predicates.add(builder.equal(entity.get("position").get("name"), positionName));
        }

        if (!StringUtils.isEmpty(docCode)) {
            predicates.add(builder.equal(entity.get("document").get("code"), docCode));
        }

        if (!StringUtils.isEmpty(citizenshipCode)) {
            predicates.add(builder.equal(entity.get("document").get("country").get("code"), citizenshipCode));
        }
        criteria.where(builder.and(predicates.toArray(new Predicate[0])));
        return criteria;
    }
}
