package com.ssafy.api.controller;

import com.ssafy.api.request.*;
import com.ssafy.api.response.StudentDeleteRes;
import com.ssafy.api.response.StudentRes;
import com.ssafy.api.service.MailService;
import com.ssafy.api.service.StudentService;
import com.ssafy.api.service.TeacherService;
import com.ssafy.common.auth.SsafyStudentDetails;
import com.ssafy.db.entity.Student;
import com.ssafy.db.entity.Teacher;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepositorySupport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	StudentService studentService;

	@Autowired
	MailService mailService;

	@Autowired
	TeacherService teacherService;


	@PostMapping("/teacher/signup")
	@ApiOperation(value = "교사 회원 가입", notes = "<strong>아이디,패스워드,이름, 연락처, 이메일, 학교이름</strong>를 통해 회원가입 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true)  TeacherRegisterPostReq teacherRegisterInfo) {
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Teacher teacher = teacherService.createTeacher(teacherRegisterInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}


	@PostMapping("/student/signup")
	@ApiOperation(value = "학생 회원 가입", notes = "<strong>아이디,패스워드,이름, 연락처, 이메일, 학교이름</strong>를 통해 회원가입 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) StudentRegisterPostReq studentRegisterInfo) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Student student = studentService.createStudent(studentRegisterInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}


	@PutMapping("/teacher/modify")
	@ApiOperation(value = "교사 정보 수정", notes = "<strong>아이디,패스워드,이름, 연락처, 이메일, 학교이름</strong>를 통해 정보를 수정한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> modify(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) TeacherModifyPutReq teacherModifyInfo) {
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Teacher teacher = teacherService.modifyTeacher(teacherModifyInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}


	@PutMapping("/student/modify")
	@ApiOperation(value = "학생 회원 정보 수정", notes = "회원 정보를 수정한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> modify(
			@RequestBody @ApiParam(value="회원수정 정보", required = true) StudentUpdatePutReq studentUpdatePutReq) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Student student = studentService.updateStudent(studentUpdatePutReq);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}


	@DeleteMapping("/teacher/withdrawal/{tchrId}")
	@ApiOperation(value = "교사 회원 정보 삭제", notes = "회원 정보를 삭제한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<?> removeteacher(@ApiParam(value = "삭제할 교사 아이디", required = true)@PathVariable String tchrId){
		teacherService.deleteTeacher(tchrId);
		return ResponseEntity.status(200).body(200);
	}


	@DeleteMapping("/student/withdrawal/{stId}")
	@ApiOperation(value = "학생 회원 정보 삭제", notes = "회원 정보를 삭제한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<?> removestudent(@ApiParam(value = "삭제할 학생 아이디", required = true)@PathVariable String stId){
		studentService.deleteStudent(stId);
		return ResponseEntity.status(200).body("OK");
	}


	@GetMapping("/student/me")
	@ApiOperation(value = "학생 회원 본인 정보 조회", notes = "로그인한 학생 본인의 정보를 응답한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<StudentRes> getStudentInfo(@ApiIgnore Authentication authentication) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		SsafyStudentDetails studentDetails = (SsafyStudentDetails)authentication.getDetails();
		String studentId =studentDetails.getUsername();
		Student student = studentService.findById(studentId);
		return ResponseEntity.status(200).body(StudentRes.of(student));
	}

	@GetMapping("/findId")
	@ApiOperation(value = "회원 아이디 찾기", notes = "회원 이메일로 아이디를 포함한 메일을 전송한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<?> findId(@RequestBody StudentFindIdPostReq studentFindIdPostReq){
		String stName = studentFindIdPostReq.getStName();
		String stEmail = studentFindIdPostReq.getStEmail();
		//이름 정보로 회원 Id 찾고, Mail 보내기
		String stId = studentService.findstIdBystName(stName);
		mailService.sendMail(stId, stEmail);
		return ResponseEntity.status(200).body(stId);
	}

	@PutMapping("/findPassword")
	@ApiOperation(value = "회원 비밀번호 변경", notes = "회원 아이디로 비밀번호를 변경한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public  ResponseEntity<? extends BaseResponseBody> findPassword(@RequestParam String stId, @RequestParam String stPassword) {

		//1. 회원 아이디에 해당하는 회원 정보 가져오기
		Student student = studentService.findById(stId);
		//2. 변경할 비밀번호
		student.setStPassword(stPassword);
		Student updateStudent = studentService.changeStudentPassword(student);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
}
