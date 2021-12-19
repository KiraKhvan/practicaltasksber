package project.dto.filter.office;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Офис")
public class OfficeFilter {

    @ApiModelProperty(value = "Идентификатор организации")
    public Long orgId;

    @ApiModelProperty(value = "Наименование")
    public String name;

    @ApiModelProperty(value = "Номер телефона")
    public String phone;

    @ApiModelProperty(value = "Активен")
    public Boolean isActive;

    @Override
    public String toString() {
        return "{orgId:" + orgId + ";" +
                "name:" + name + ";" +
                "phone:" + phone + ";" +
                "isActive:" + isActive +
                "}";
    }
}
