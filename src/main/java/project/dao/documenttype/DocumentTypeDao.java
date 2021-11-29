package project.dao.documenttype;

import project.model.DocumentType;

import java.util.List;

/**
 * DAO для работы с DocumentType
 */
public interface DocumentTypeDao {

    /**
     * Получить DocumentType по названию типа документа
     *
     * @param name
     * @return
     */
    DocumentType loadByName(String name);

    /**
     * Получить объекты DocumentType
     *
     * @return
     */
    List<DocumentType> getAllDocumentTypeList();
}
