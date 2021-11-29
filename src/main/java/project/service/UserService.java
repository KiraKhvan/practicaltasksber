package project.service;

import project.view.UserListView;
import project.view.UserView;

import java.util.Date;
import java.util.List;

public interface UserService {

    /**
     * Добавить нового пользователя в БД
     *
     * @param
     */
    boolean save(
            Long officeId,
            String firstName,
            String secondName,
            String middleName,
            String positionName,
            String phone,
            String docCode,
            String docName,
            Integer docNumber,
            Date docDate,
            String citizenshipCode,
            Boolean isIdentified
    );

    /**
     * Изменить информацию о пользователе
     *
     * @return {@User}
     */
    boolean update(
            Long userId,
            Long officeId,
            String firstName,
            String secondName,
            String middleName,
            String positionName,
            String phone,
            String docName,
            Integer docNumber,
            Date docDate,
            String citizenshipCode,
            Boolean isIdentified
    );

    /**
     * Получить список пользователей
     *
     * @return {@User}
     */
    List<UserListView> getUsers(
            Long officeId,
            String firstName,
            String lastName,
            String middleName,
            String position,
            String docCode,
            String citizenshipCode
    );

    /**
     * Получить пользователя по идентификатору
     *
     * @return {@User}
     */
    UserView getUser(Long id);
}
