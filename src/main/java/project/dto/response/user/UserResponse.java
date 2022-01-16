package project.dto.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import project.dto.response.PositionDto;

import java.util.List;

@ApiModel(description = "Пользователь")
public class UserResponse {

    @ApiModelProperty(value = "Уникальный идентификатор")
    public String id;

    @ApiModelProperty(value = "Имя")
    public String firstName;

    @ApiModelProperty(value = "Фамилия")
    public String secondName;

    @ApiModelProperty(value = "Отчество")
    public String middleName;

    @ApiModelProperty(value = "Должности")
    public List<PositionDto> positions;

    @ApiModelProperty(value = "Номер телефона")
    public String phone;

    @ApiModelProperty(value = "Вид документа")
    public String docName;

    @ApiModelProperty(value = "Номер документа")
    public String docNumber;

    @ApiModelProperty(value = "Дата документа")
    public String docDate;

    @ApiModelProperty(value = "Гражданство")
    public String citizenshipName;

    @ApiModelProperty(value = "Код страны")
    public String citizenshipCode;

    @ApiModelProperty(value = "Идентифицируется")
    public String isIdentified;

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + "\"," +
                "\"firstName\":\"" + firstName + "\"," +
                "\"secondName\":\"" + secondName + "\"," +
                "\"middleName\":\"" + middleName + "\"," +
                "\"position\":\"" + positions + "\"," +
                "\"docDate\":\"" + docDate + "\"," +
                "\"docName\":\"" + docName + "\"," +
                "\"docNumber\":\"" + docNumber + "\"," +
                "\"citizenshipName\":\"" + citizenshipName + "\"," +
                "\"citizenshipCode\":\"" + citizenshipCode + "\"," +
                "\"isIdentified\":\"" + isIdentified +
                "\"}";
    }
}
