package com.ssafy.api.request.notice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ApiModel("NoticeRegisterPostReq")
public class NoticeRegisterPostReq {
	@ApiModelProperty(name = "클래스 ID")
	Integer studyId;
	@ApiModelProperty(name = "선생 ID", example = "tchr_id")
	String tchrId;
	@ApiModelProperty(name = "공지사항 제목", example = "공지사항입니다.")
	String noticeTitle;
	@ApiModelProperty(name = "공지사항 본문", example = "공지사항 내용")
	String noticeContent;
	@ApiModelProperty
	List<MultipartFile> noticeFile;
}
