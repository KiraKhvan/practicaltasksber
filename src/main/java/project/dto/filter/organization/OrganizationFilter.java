package project.dto.filter.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Организация")
public class OrganizationFilter {

    @ApiModelProperty(value = "Наименование")
    public String name;

    @ApiModelProperty(value = "ИНН")
    public String inn;

    @ApiModelProperty(value = "Активна")
    public Boolean isActive;

    @Override
    public String toString() {
        return "{" +
                "name:" + name + ";" +
                "inn:" + inn + ";" +
                "isActive:" + isActive +
                "}";
    }
}
