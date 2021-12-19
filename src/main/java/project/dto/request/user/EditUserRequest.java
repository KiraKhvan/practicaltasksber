package project.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "Пользователь")
public class EditUserRequest {

    @ApiModelProperty(value = "Уникальный идентификатор")
    public Long id;

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

    @ApiModelProperty(value = "Номер телефона")
    public String phone;

    @ApiModelProperty(value = "Код документа")
    public String docCode;

    @ApiModelProperty(value = "Вид документа")
    public String docName;

    @ApiModelProperty(value = "Номер документа")
    public String docNumber;

    @ApiModelProperty(value = "Дата документа")
    public Date docDate;

    @ApiModelProperty(value = "Гражданство")
    public String citizenshipName;

    @ApiModelProperty(value = "Код страны")
    public String citizenshipCode;

    @ApiModelProperty(value = "Идентифицируется")
    public Boolean isIdentified;

    @Override
    public String toString() {
        return "{" +
                "id:" + id + ";" +
                "officeId:" + officeId + ";" +
                "firstName:" + firstName + ";" +
                "lastName:" + lastName + ";" +
                "middleName:" + middleName + ";" +
                "position:" + position + ";" +
                "docCode:" + docCode + ";" +
                "docDate:" + docDate + ";" +
                "docName:" + docName + ";" +
                "docNumber:" + docNumber + ";" +
                "citizenshipName:" + citizenshipName + ";" +
                "citizenshipCode:" + citizenshipCode + ";" +
                "isIdentified:" + isIdentified +
                "}";
    }
}
