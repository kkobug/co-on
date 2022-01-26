package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyRoomStudentDelterRequest")
public class StudyRoomStudentDeleteReq {
    @ApiModelProperty(name = "수업 ID", example = "1")
    int studyId;
    @ApiModelProperty(name = "학생 ID", example = "st_id")
    String stId;
}
