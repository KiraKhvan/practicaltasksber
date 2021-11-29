package project.dao.position;

import project.model.Position;

/**
 * DAO для работы с Position
 */
public interface PositionDao {

    /**
     * Получить Position по названию должности
     *
     * @param name
     * @return
     */
    Position loadByName(String name);
}
