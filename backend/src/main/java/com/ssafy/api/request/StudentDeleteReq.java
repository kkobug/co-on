package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudentDeleteRequest")
public class StudentDeleteReq {
    @ApiModelProperty(name="학생 ID", example = "ssafy_web")
    String st_id;
}
