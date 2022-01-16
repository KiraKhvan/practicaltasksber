package project.dto.response.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "Организация")
public class OrganizationResponse {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор")
    public String id;

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
        return "{" +
                "\"id\":\"" + id + "\"," +
                "\"name\":\"" + name + "\"," +
                "\"fullName\":\"" + fullName + "\"," +
                "\"inn\":\"" + inn + "\"," +
                "\"kpp\":\"" + kpp + "\"," +
                "\"address\":\"" + address + "\"," +
                "\"phone\":\"" + phone + "\"," +
                "\"isActive\":\"" + isActive +
                "\"}";
    }
}
