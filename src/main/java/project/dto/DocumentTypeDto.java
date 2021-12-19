package project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Тип документа")
public class DocumentTypeDto {

    @ApiModelProperty(value = "Наименование")
    public String name;

    @ApiModelProperty(value = "Код")
    public String code;

    @Override
    public String toString() {
        return "{code:" + code + ";name:" + name + ";";
    }
}
