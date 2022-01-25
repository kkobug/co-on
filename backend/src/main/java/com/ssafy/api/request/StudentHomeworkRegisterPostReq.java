package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudentHomeworkRegisterPostReq")
public class StudentHomeworkRegisterPostReq {
    @ApiModelProperty(name = "학생 과제 ID", example = "1")
    Integer stHwId;
    @ApiModelProperty(name = "교수 과제 ID", example = "1")
    Integer hwId;
    @ApiModelProperty(name = "클래스 ID", example = "1")
    Integer studyId;
    @ApiModelProperty(name = "학생 점수", example = "1")
    Integer stHwscore;
    @ApiModelProperty(name = "출제 교사 ID", example = "tchr_id")
    String tchrId;
    @ApiModelProperty(name = "제출 학생 ID", example = "st_id")
    String stId;
    @ApiModelProperty(name = "제출과제 내용", example = "수학 과제 제출")
    String stHwContent;
    @ApiModelProperty(name = "과제 제출일자", example= "")
    String stHwposted;
}
