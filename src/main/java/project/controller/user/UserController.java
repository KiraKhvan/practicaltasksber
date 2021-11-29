package project.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.service.UserService;
import project.view.UserListView;
import project.view.UserView;

import java.util.Date;
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
    public void save(
            @RequestParam(required = false) Long officeId,
            @RequestParam String firstName,
            @RequestParam(required = false) String secondName,
            @RequestParam(required = false) String middleName,
            @RequestParam String position,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String docCode,
            @RequestParam(required = false) String docName,
            @RequestParam(required = false) Integer docNumber,
            @RequestParam(required = false) Date docDate,
            @RequestParam(required = false) String citizenshipCode,
            @RequestParam(required = false) Boolean isIdentified
    ) {
        userService.save(
                officeId,
                firstName,
                secondName,
                middleName,
                position,
                phone,
                docCode,
                docName,
                docNumber,
                docDate,
                citizenshipCode,
                isIdentified
        );
    }

    @ApiOperation(value = "Изменить пользователя", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/update")
    public void update(
            @RequestParam Long id,
            @RequestParam(required = false) Long officeId,
            @RequestParam String firstName,
            @RequestParam(required = false) String secondName,
            @RequestParam(required = false) String middleName,
            @RequestParam String position,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String docName,
            @RequestParam(required = false) Integer docNumber,
            @RequestParam(required = false) Date docDate,
            @RequestParam(required = false) String citizenshipCode,
            @RequestParam(required = false) Boolean isIdentified
    ) {
        userService.update(
                id,
                officeId,
                firstName,
                secondName,
                middleName,
                position,
                phone,
                docName,
                docNumber,
                docDate,
                citizenshipCode,
                isIdentified
        );
    }

    @ApiOperation(value = "Получить список всех пользователей", httpMethod = "GET")
    @GetMapping("/list")
    public List<UserListView> users(
            @RequestParam Long officeId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String middleName,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String docCode,
            @RequestParam(required = false) String citizenshipCode
    ) {
        return userService.getUsers(
                officeId,
                firstName,
                lastName,
                middleName,
                position,
                docCode,
                citizenshipCode
        );
    }

    @ApiOperation(value = "Получить пользователя по идентификатору", httpMethod = "GET")
    @GetMapping("/{id}")
    public UserView user(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
