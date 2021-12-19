package project.dto.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Пользователь")
public class UserListResponse {

    @ApiModelProperty(value = "Уникальный идентификатор")
    public String id;

    @ApiModelProperty(value = "Имя")
    public String firstName;

    @ApiModelProperty(value = "Фамилия")
    public String secondName;

    @ApiModelProperty(value = "Отчество")
    public String middleName;

    @ApiModelProperty(value = "Должность")
    public String position;

    @Override
    public String toString() {
        return "{id:" + id + ";" +
                "firstName:" + firstName + ";" +
                "secondName:" + secondName + ";" +
                "middleName:" + middleName + ";" +
                "position:" + position +
                "}";
    }
}
