package project.dao.document;

import project.model.Document;

/**
 * DAO для работы с Document
 */
public interface DocumentDao {

    /**
     * Получить Document по идентификатору пользователя
     *
     * @param userId
     * @return
     */
    Document loadByUserId(Long userId);

    /**
     * Сохранить новый Document
     *
     * @param document
     */
    void save(Document document);

    /**
     * Сохранить Document
     *
     * @param document
     */
    void update(Document document);
}
