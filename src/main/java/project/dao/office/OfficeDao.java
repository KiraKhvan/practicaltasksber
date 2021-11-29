package project.dao.office;

import project.model.Office;

import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return
     */
    Office loadById(Long id);

    /**
     * Сохранить нового Office
     *
     * @param office
     */
    void save(Office office);

    /**
     * Сохранить Office
     *
     * @param office
     */
    void update(Office office);

    /**
     * Получить отфильтрованные объекты Office
     *
     * @return
     */
    List<Office> getFilteredOfficeList(
            Long orgId,
            String name,
            String phone,
            Boolean isActive
    );
}
