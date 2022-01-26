package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudentHomeworkUpdatePutRequest")
public class StudentHomeworkUpdatePutReq {
    @ApiModelProperty(name = "학생 과제 ID", example = "null")
    int stHwId;
    @ApiModelProperty(name = "제출과제 내용", example = "도덕 과제 제출")
    String stHwContent;
}