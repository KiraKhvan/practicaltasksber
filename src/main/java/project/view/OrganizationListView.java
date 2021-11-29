package project.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "Организация")
public class OrganizationListView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор")
    public String id;
    @ApiModelProperty(value = "ИНН")
    public String inn;

    @ApiModelProperty(value = "Активна")
    public Boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id + ";" +
                "inn:" + inn + ";" +
                "isActive:" + isActive +
                "}";
    }
}
