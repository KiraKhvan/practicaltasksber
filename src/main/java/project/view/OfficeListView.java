package project.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Офис")
public class OfficeListView {

    @ApiModelProperty(value = "Уникальный идентификатор")
    public String id;

    @ApiModelProperty(value = "Наименование")
    public String name;

    @ApiModelProperty(value = "Активен")
    public String isActive;

    @Override
    public String toString() {
        return "{id:" + id + ";" +
                "name:" + name + ";" +
                "isActive:" + isActive +
                "}";
    }
}
