package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("TeacherModifyPutRequest")
public class TeacherModifyPutReq {
	@ApiModelProperty(name="교사 ID", example="Teacher_id")
	String tchr_id;
	@ApiModelProperty(name="교사 name", example="Teacher_name")
	String tchr_name;
	@ApiModelProperty(name="교사 email", example="Teacher_email")
	String tchr_email;
	@ApiModelProperty(name="교사 Password", example="Teacher_password")
	String tchr_password;
	@ApiModelProperty(name="교사 contact", example="Teacher_contact")
	String tchr_contact;
	@ApiModelProperty(name="교사 school", example="Teacher_school")
	String tchr_school;
}
