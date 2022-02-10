package com.ssafy.api.request.studyroomdetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudyRoomDetailPutRequest")
public class StudyRoomDetailPutReq {
    @ApiModelProperty(name = "수업ID", example = "1")
    int studyId;
    @ApiModelProperty(name = "학생ID" ,example = "st_id1")
    String stId;
    @ApiModelProperty(name = "추가할 점수", example = "5")
    int point;
}
