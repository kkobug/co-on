package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
@ApiModel("StudyRoomDeleteRequest")
public class StudyRoomDeleteRes {
    @ApiModelProperty(name = "선생님 ID", example = "test1")
    String tchrId;
    @ApiModelProperty(name = "교실 이름", example = "수학")
    String studyName;
}
