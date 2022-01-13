package project.dto.request.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Организация")
public class EditOrganizationRequest {

    @ApiModelProperty(value = "Уникальный идентификатор")
    public Long id;

    @ApiModelProperty(value = "Наименование")
    public String name;

    @ApiModelProperty(value = "Полное наименование")
    public String fullName;

    @ApiModelProperty(value = "ИНН")
    public String inn;

    @ApiModelProperty(value = "КПП")
    public String kpp;

    @ApiModelProperty(value = "Адрес")
    public String address;

    @ApiModelProperty(value = "Номер телефона")
    public String phone;

    @ApiModelProperty(value = "Активна")
    public Boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id + ";" +
                "name:" + name + ";" +
                "fullName:" + fullName + ";" +
                "inn:" + inn + ";" +
                "kpp:" + kpp + ";" +
                "address:" + address + ";" +
                "phone:" + phone + ";" +
                "isActive:" + isActive +
                "}";
    }
}
