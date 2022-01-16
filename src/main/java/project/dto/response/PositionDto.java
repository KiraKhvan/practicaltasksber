package project.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Должность")
public class PositionDto {

    @ApiModelProperty(value = "Наименование")
    public String name;

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name + "\"" +
                "}";
    }
}
