package com.ssafy.api.controller;

import com.ssafy.api.request.HomeworkDeleteReq;
import com.ssafy.api.request.HomeworkModifyReq;
import com.ssafy.api.request.HomeworkRegisterPostReq;
import com.ssafy.api.request.TeacherModifyPutReq;
import com.ssafy.api.response.HomeworkInfoRes;
import com.ssafy.api.service.HomeworkService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.Teacher;
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

    @GetMapping("/teacher/{tchrId}")
    @ApiOperation(value = "교사가 출제한 과제 조회", notes = "<strong>교사아이디</strong>를 통해 조회 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<Homework>> tchr_list(
            @PathVariable @ApiParam(value = "과제 출제 정보", required = true)String tchrId){
        List<Homework> list = homeworkService.findHomeworkByTchrId(tchrId);
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/study/{studyId}")
    @ApiOperation(value = "수업에 포함된 과제 조회", notes = "<strong>수업아이디</strong>를 통해 조회 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<Homework>> study_list(
            @PathVariable @ApiParam(value = "과제 출제 정보", required = true)Integer studyId) {
        List<Homework> list = homeworkService.findHomeworkByStudyId(studyId);
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/detail/{hwId}")
    @ApiOperation(value = "과제의 상세 내용 조회", notes = "<strong></strong>를 통해 조회 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<HomeworkInfoRes> homework_detail(@RequestParam Integer hwId) {
        Homework homework = homeworkService.findHomeworkByHwId(hwId);
        return ResponseEntity.status(200).body(HomeworkInfoRes.of(homework));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "과제 삭제", notes = "<strong>과제, 교사 ID</strong>을 통해 (교사가) 과제를 삭제 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> delete(@RequestBody HomeworkDeleteReq homeworkDeleteReq){
        int hwId = homeworkDeleteReq.getHwId();
        String tchrId = homeworkDeleteReq.getTchrId();
        homeworkService.deleteHomework(hwId, tchrId);
        return ResponseEntity.status(200).body("OK");
    }

    @PutMapping("/modify/{hwId}")
    @ApiOperation(value = "과제 수정", notes = "<strong>과제 ID</strong>을 통해 과제를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public  ResponseEntity<? extends BaseResponseBody> modifyHomework(@RequestParam Integer hwId, @RequestBody HomeworkModifyReq homeworkModifyReq) {
        Homework homework = homeworkService.updateHomework(hwId, homeworkModifyReq);
        if (homework.getHwId() != hwId) return ResponseEntity.status(404).body(BaseResponseBody.of(404,"False"));
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}
