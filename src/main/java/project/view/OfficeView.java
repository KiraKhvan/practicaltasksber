package project.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Офис")
public class OfficeView {

    @ApiModelProperty(value = "Уникальный идентификатор")
    public String id;

    @ApiModelProperty(value = "Наименование")
    public String name;

    @ApiModelProperty(value = "Адрес")
    public String address;

    @ApiModelProperty(value = "Номер телефона")
    public String phone;

    @ApiModelProperty(value = "Активен")
    public String isActive;

    @Override
    public String toString() {
        return "{id:" + id + ";" +
                "name:" + name + ";" +
                "address:" + address + ";" +
                "phone:" + phone + ";" +
                "isActive:" + isActive +
                "}";
    }
}
