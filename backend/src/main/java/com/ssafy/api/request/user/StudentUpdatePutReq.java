package com.ssafy.api.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudentUpdatePutRequest")
public class StudentUpdatePutReq {
    @ApiModelProperty(name = "수정할 학생 ID", example = "st_id")
    String st_id;
    @ApiModelProperty(name = "학생 Name", example = "kimssafy")
    String st_name;
    @ApiModelProperty(name = "학생 Email", example = "ssafy@ssafy.com")
    String st_email;
    @ApiModelProperty(name = "학생 Password", example = "1234")
    String st_password;
    @ApiModelProperty(name = "학생 Contact", example = "010-1234-1234")
    String st_contact;
    @ApiModelProperty(name = "학생 School", example = "싸피중학교")
    String st_school;
    @ApiModelProperty(name = "학생 birthday", example = "20050305")
    String st_birthday;
}
