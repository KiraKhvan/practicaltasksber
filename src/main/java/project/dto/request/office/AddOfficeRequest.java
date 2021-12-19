package project.dto.request.office;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Офис")
public class AddOfficeRequest {

    @ApiModelProperty(value = "Уникальный идентификатор организации")
    public Long orgId;

    @ApiModelProperty(value = "Наименование")
    public String name;

    @ApiModelProperty(value = "Адрес")
    public String address;

    @ApiModelProperty(value = "Номер телефона")
    public String phone;

    @ApiModelProperty(value = "Активен")
    public Boolean isActive;

    @Override
    public String toString() {
        return "{" +
                "orgId:" + orgId + ";" +
                "name:" + name + ";" +
                "address:" + address + ";" +
                "phone:" + phone + ";" +
                "isActive:" + isActive +
                "}";
    }
}
