package com.ssafy.api.controller;

import com.ssafy.api.request.studyroom.StudyRoomAddPostReq;
import com.ssafy.api.service.studyroom.StudyRoomDetailService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Studyroom;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "수업Detail API",tags = {"StudyRoomDetail"})
@RestController
@RequestMapping("api/v1/studyRoomDetail")
public class StudyRoomDetailController {
    @Autowired
    StudyRoomDetailService studyRoomDetailService;

    @GetMapping("/student/list/{stId}")
    @ApiOperation(value = "학생 수업 조회", notes = "<strong>학생아이디</strong>를 통해 조회 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<Studyroom>> studentlist(
            @PathVariable @ApiParam(value = "학생수업 정보", required = true)String stId){
        List<Studyroom> list = studyRoomDetailService.findStudyroombystId(stId);
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/student/studylist/{studyId}")
    @ApiOperation(value = "학생 수업 조회", notes = "<strong>학생아이디</strong>를 통해 조회 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<Object[]>> studylistlist(
            @PathVariable @ApiParam(value = "학생 리스트 정보", required = true)int studyId){
        List<Object[]> list = studyRoomDetailService.findStudyroombystudyId(studyId);
        return ResponseEntity.status(200).body(list);
    }

    @PostMapping("/teacher/add")
    @ApiOperation(value = "학생 추가", notes = "<strong>수업ID, 교사ID, 학생 ID</strong>를 통해 수업에 학생을 추가한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> add(
            @RequestBody @ApiParam(value = "수업개설 정보", required = true) StudyRoomAddPostReq studyRoomAddPostReq){
        studyRoomDetailService.addStudent(studyRoomAddPostReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}
