package project.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import project.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        String queryString = buildQueryStringForFilterUserList(
                firstName,
                lastName,
                middleName,
                positionName,
                docCode,
                citizenshipCode
        );

        Query query = em.createQuery(queryString);

        prepareQueryForFilterUserList(
                query,
                officeId,
                firstName,
                lastName,
                middleName,
                positionName,
                docCode,
                citizenshipCode
        );

        return query.getResultList();
    }

    protected String buildQueryStringForFilterUserList(
            String firstName,
            String lastName,
            String middleName,
            String positionName,
            String docCode,
            String citizenshipCode
    ) {
        StringBuilder builder = new StringBuilder("SELECT u FROM User u where u.office.id = :officeId");

        if (!StringUtils.isEmpty(firstName)) {
            builder.append(" ");
            builder.append("and u.firstName = :firstName");
        }

        if (!StringUtils.isEmpty(lastName)) {
            builder.append(" ");
            builder.append("and u.lastName = :lastName");
        }

        if (!StringUtils.isEmpty(middleName)) {
            builder.append(" ");
            builder.append("and u.middleName = :middleName");
        }

        if (!StringUtils.isEmpty(positionName)) {
            builder.append(" ");
            builder.append("and u.position.name = :positionName");
        }

        if (!StringUtils.isEmpty(docCode)) {
            builder.append(" ");
            builder.append("and u.document.code = :docCode");
        }

        if (!StringUtils.isEmpty(citizenshipCode)) {
            builder.append(" ");
            builder.append("and u.document.country.code = :citizenshipCode");
        }

        return builder.toString();
    }

    protected void prepareQueryForFilterUserList(
            Query query,
            Long officeId,
            String firstName,
            String lastName,
            String middleName,
            String positionName,
            String docCode,
            String citizenshipCode
    ) {
        query.setParameter("officeId", officeId);

        if (!StringUtils.isEmpty(firstName)) {
            query.setParameter("firstName", firstName);
        }

        if (!StringUtils.isEmpty(lastName)) {
            query.setParameter("lastName", lastName);
        }

        if (!StringUtils.isEmpty(middleName)) {
            query.setParameter("middleName", middleName);
        }

        if (!StringUtils.isEmpty(positionName)) {
            query.setParameter("positionName", positionName);
        }

        if (!StringUtils.isEmpty(docCode)) {
            query.setParameter("docCode", docCode);
        }

        if (!StringUtils.isEmpty(citizenshipCode)) {
            query.setParameter("citizenshipCode", citizenshipCode);
        }
    }
}
