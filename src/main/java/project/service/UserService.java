package project.service;

import project.dto.filter.user.UserFilter;
import project.dto.request.user.AddUserRequest;
import project.dto.request.user.EditUserRequest;
import project.dto.response.user.UserListResponse;
import project.dto.response.user.UserResponse;

import java.util.List;

public interface UserService {

    /**
     * Добавить нового пользователя в БД
     *
     * @param
     */
    boolean save(AddUserRequest request);

    /**
     * Изменить информацию о пользователе
     *
     * @return {@User}
     */
    boolean update(EditUserRequest request);

    /**
     * Получить список пользователей
     *
     * @return {@User}
     */
    List<UserListResponse> getUsers(UserFilter filter);

    /**
     * Получить пользователя по идентификатору
     *
     * @return {@User}
     */
    UserResponse getUser(Long id);
}
