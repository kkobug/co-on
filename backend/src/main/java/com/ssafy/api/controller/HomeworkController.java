package com.ssafy.api.controller;

import com.ssafy.api.request.HomeworkRegisterPostReq;
import com.ssafy.api.service.HomeworkService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Homework;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "과제 API", tags = {"Homework"})
@RestController
@RequestMapping("/api/v1/homework")
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;

    @PostMapping("/create")
    @ApiOperation(value = "과제 출제", notes = "<strong>과제 제목, 내용, 기한, 첨부</strong>를 통해 개설한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> regist(
            @RequestBody @ApiParam(value = "과제 출제 정보", required = true)HomeworkRegisterPostReq homeworkRegisterPostReq){
        Homework homework = homeworkService.createHomework(homeworkRegisterPostReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping("/{tchrId}")
    @ApiOperation(value = "교사가 출제한 과제 조회", notes = "<strong>교사아이디</strong>를 통해 조회 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<String>> tchr_list(
            @PathVariable @ApiParam(value = "과제 출제 정보", required = true)String tchrId){
        List<String> list = homeworkService.findHomeworkByTchrId(tchrId);
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/{studyId}")
    @ApiOperation(value = "수업에 포함된 과제 조회", notes = "<strong>수업아이디</strong>를 통해 조회 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<String>> study_list(
            @PathVariable @ApiParam(value = "과제 출제 정보", required = true)Integer studyId){
        List<String> list = homeworkService.findHomeworkByStudyId(studyId);
        return ResponseEntity.status(200).body(list);
    }
}
