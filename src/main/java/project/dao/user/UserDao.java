package project.dao.user;

import project.model.User;

import java.util.List;

/**
 * DAO для работы с User
 */
public interface UserDao {

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return
     */
    User loadById(Long id);

    /**
     * Сохранить нового User
     *
     * @param user
     */
    void save(User user);

    /**
     * Сохранить User
     *
     * @param user
     */
    void update(User user);

    /**
     * Получить отфильтрованные объекты User
     *
     * @return
     */
    List<User> getFilteredUserList(
            Long officeId,
            String firstName,
            String lastName,
            String middleName,
            String positionName,
            String docCode,
            String citizenshipCode
    );
}
