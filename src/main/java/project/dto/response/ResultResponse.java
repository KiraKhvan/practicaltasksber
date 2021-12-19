package project.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ResultResponse {

    @ApiModelProperty
    public String result;

    public ResultResponse(boolean result) {
        if (result) {
            this.result = "success";
        } else {
            this.result = "error";
        }
    }

    @Override
    public String toString() {
        return "{result:" + result + "}";
    }
}
