package project.dto.filter.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Пользователь")
public class UserFilter {

    @ApiModelProperty(value = "Идентификатор офиса")
    public Long officeId;

    @ApiModelProperty(value = "Имя")
    public String firstName;

    @ApiModelProperty(value = "Фамилия")
    public String lastName;

    @ApiModelProperty(value = "Отчество")
    public String middleName;

    @ApiModelProperty(value = "Должность")
    public String position;

    @ApiModelProperty(value = "Код документа")
    public String docCode;

    @ApiModelProperty(value = "Код страны")
    public String citizenshipCode;

    @Override
    public String toString() {
        return "{" +
                "officeId:" + officeId + ";" +
                "firstName:" + firstName + ";" +
                "lastName:" + lastName + ";" +
                "middleName:" + middleName + ";" +
                "position:" + position + ";" +
                "docCode:" + docCode + ";" +
                "citizenshipCode:" + citizenshipCode + ";" +
                "}";
    }
}
