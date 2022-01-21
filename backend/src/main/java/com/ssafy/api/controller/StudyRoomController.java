package com.ssafy.api.controller;

import com.ssafy.api.request.StudyRoomDeleteRes;
import com.ssafy.api.request.StudyRoomRegisterPostReq;
import com.ssafy.api.service.StudyRoomService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Studyroom;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "수업 API", tags = {"StudyRoom"})
@RestController
@RequestMapping("/api/v1/studyRoom")
public class StudyRoomController {
    @Autowired
    StudyRoomService studyRoomService;

    @PostMapping("/create")
    @ApiOperation(value = "수업 개설", notes = "<strong>교사아이디, 교실이름 교실설명</strong>을 통해 개설 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> regist(
            @RequestBody @ApiParam(value = "수업개설 정보", required = true)StudyRoomRegisterPostReq studyRoomRegisterPostReq){
        Studyroom list= studyRoomService.createStudyRoom(studyRoomRegisterPostReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping("/list/{tchrId}")
    @ApiOperation(value = "교사 수업 조회", notes = "<strong>교사아이디</strong>를 통해 조회 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<String>> list(
            @PathVariable @ApiParam(value = "수업개설 정보", required = true)String tchrId){
        List<String> list = studyRoomService.findstudyNamebytchrId(tchrId);
        return ResponseEntity.status(200).body(list);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "수업 삭제", notes = "<strong>교사아이디, 수업 제목</strong>을 통해 삭제 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> delete(@RequestBody StudyRoomDeleteRes studyRoomDeleteRes){
        String tchrId = studyRoomDeleteRes.getTchrId();
        String studyName = studyRoomDeleteRes.getStudyName();
        studyRoomService.deleteStudyRoom(tchrId, studyName);
        return ResponseEntity.status(200).body("OK");
    }

    @GetMapping("list/{stId}")
    @ApiOperation(value = "학생 수업 조회", notes = "<strong>학생아이디</strong>를 통해 조회 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<String>> listStudyRoom(
            @PathVariable @ApiParam(value = "수업개설 정보", required = true)String tchrId){
        List<String> list = studyRoomService.findstudyNamebytchrId(tchrId);
        return ResponseEntity.status(200).body(list);
    }
}