package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("NoticeUpdatePutRequest")
public class NoticeUpdatePutReq {
    @ApiModelProperty(name = "notice ID", example = "null")
    int noticeId;
//    @ApiModelProperty(name = "클래스 ID", example = "1")
//    int studyId;
//    @ApiModelProperty(name = "교수 ID", example = "1")
//    String tchrId;
    @ApiModelProperty(name = "공지사항 제목", example = "공지사항입니다.")
    String noticeTitle;
    @ApiModelProperty(name = "공지사항 본문", example = "공지사항 내용")
    String noticeContent;
    @ApiModelProperty(name = "공지사항 작성일자", example= "")
    String noticePosted;
}
