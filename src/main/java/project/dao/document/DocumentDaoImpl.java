package project.dao.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.model.Document;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * {@inheritDoc}
 */
@Repository
@Transactional
public class DocumentDaoImpl implements DocumentDao {

    private final EntityManager em;

    @Autowired
    public DocumentDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Document loadByUserId(Long userId) {
        TypedQuery<Document> query = em.createQuery(
                        "SELECT d FROM Document d where d.user.id = :userId",
                        Document.class
                )
                .setParameter("userId", userId);
        return query.getSingleResult();
    }

    @Override
    public void save(Document document) {
        em.persist(document);
    }

    @Override
    public void update(Document document) {
        em.merge(document);
    }
}
