package project.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.dto.filter.user.UserFilter;
import project.dto.request.user.AddUserRequest;
import project.dto.request.user.EditUserRequest;
import project.dto.response.ResultResponse;
import project.dto.response.user.UserListResponse;
import project.dto.response.user.UserResponse;
import project.service.UserService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "UserController", description = "Управление информацией о пользователях")
@RestController
@RequestMapping(value = "/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Добавить нового пользователя", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public ResultResponse save(@RequestBody AddUserRequest request) {
        boolean result = userService.save(request);
        return new ResultResponse(result);
    }

    @ApiOperation(value = "Изменить пользователя", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/update")
    public ResultResponse update(@RequestBody EditUserRequest request) {
        boolean result = userService.update(request);
        return new ResultResponse(result);
    }

    @ApiOperation(value = "Получить список всех пользователей", httpMethod = "POST")
    @PostMapping("/list")
    public List<UserListResponse> users(@RequestBody UserFilter filter) {
        return userService.getUsers(filter);
    }

    @ApiOperation(value = "Получить пользователя по идентификатору", httpMethod = "GET")
    @GetMapping("/{id}")
    public UserResponse user(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
