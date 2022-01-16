package project.dao.documenttype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.model.DocumentType;

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
public class DocumentTypeDaoImpl implements DocumentTypeDao {

    private final EntityManager em;

    @Autowired
    public DocumentTypeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public DocumentType loadByName(String name) {
        CriteriaQuery<DocumentType> criteria = buildCriteria("name", name);
        TypedQuery<DocumentType> query = em.createQuery(criteria);
        Object result = query.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
        if (result != null) {
            return (DocumentType) result;
        } else {
            return null;
        }
    }

    @Override
    public List<DocumentType> getAllDocumentTypeList() {
        TypedQuery<DocumentType> query = em.createQuery("SELECT d FROM DocumentType d", DocumentType.class);
        return query.getResultList();
    }

    private CriteriaQuery<DocumentType> buildCriteria(String attrName, String attrValue) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocumentType> criteria = builder.createQuery(DocumentType.class);

        Root<DocumentType> entity = criteria.from(DocumentType.class);
        criteria.where(builder.equal(entity.get(attrName), attrValue));

        return criteria;
    }
}
