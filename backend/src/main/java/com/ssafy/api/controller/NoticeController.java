package com.ssafy.api.controller;

import com.ssafy.api.request.NoticeDeleteRes;
import com.ssafy.api.request.NoticeRegisterPostReq;
import com.ssafy.api.request.NoticeUpdatePutReq;
import com.ssafy.api.response.NoticeFindID;
import com.ssafy.api.service.*;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Notice;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"Notice"})
@RestController
@RequestMapping("/api/v1")
public class NoticeController {
	@Autowired
	NoticeService noticeService;

	@PostMapping("/notice/create")
	@ApiOperation(value = "교사 공지사항 입력", notes = "<strong>클래스 id, 선생 id, 공지사항 제목, 공지사항 내용</strong>을 입력하여 공지사항을 등록한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "잘못된 요청"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> register(
			@ApiParam(value="공지사항 작성", required = true)
			@ModelAttribute
					NoticeRegisterPostReq noticeRegisterPostReq
//			@RequestParam
//					String noticeTitle,
//			@RequestParam
//					String noticeContent,
//			@RequestParam
//					Integer studyId,
//			@RequestParam
//					String tchrId,
//			@ModelAttribute
//					MultipartFile noticeFile
			) throws Exception{
//				System.out.println("!!!!!!!!!!!!!!!!!!!!!");
//				System.out.println(noticeRegisterPostReq.getNoticeTitle());
//				System.out.println(noticeRegisterPostReq.getNoticeContent());
//				System.out.println(noticeRegisterPostReq.getStudyId());
//				System.out.println(noticeRegisterPostReq.getNoticeFile());
//				System.out.println("!!!!!!!!!!!!!!!!!!!!!");
				noticeService.createNotice(noticeRegisterPostReq);
				return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	@DeleteMapping("/notice/delete")
	@ApiOperation(value = "공지사항 삭제", notes = "<strong>공지사항, 교사 아이디</strong>를 통해 삭제 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<?> delete(@RequestBody NoticeDeleteRes noticeDeleteRes){
		Long noticeId = (long) noticeDeleteRes.getNoticeId();
		String tchrId = noticeDeleteRes.getTchrId();
		noticeService.deleteNotice(noticeId, tchrId);
		return ResponseEntity.status(200).body("OK");
	}

	@GetMapping("/notice/{noticeId}")
	@ApiOperation(value = "공지사항 정보 조회", notes = "선택한 공지사항의 정보를 조회한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<NoticeFindID> findNoticetId(
			@PathVariable @ApiParam(value = "공지 상세 정보") Long noticeId) {
		Notice notice = noticeService.findBynoticeId(noticeId);
		return ResponseEntity.status(200).body(NoticeFindID.of(notice));
	}

	@PutMapping("/notice/modify/{noticeId}")
	@ApiOperation(value = "공지사항 수정", notes = "공지사항을 수정한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> modify(
			@PathVariable @ApiParam(value = "공지사항 수정") Long noticeId,
			@RequestBody NoticeUpdatePutReq noticeUpdatePutReq) {
		Notice notice = noticeService.updateNotice(Math.toIntExact(noticeId), noticeUpdatePutReq);
		if(notice.getNoticeId() != noticeId) return ResponseEntity.status(404).body(BaseResponseBody.of(404,"False"));
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));

	}

	@GetMapping("/notice/study/list/{studyId}")
	@ApiOperation(value = "수업에 포함된 공지사항 조회", notes = "수업 아이디를 통해 조회한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<Notice>> study_list(
			@PathVariable @ApiParam(value = "공지사항 정보", required = true)Integer studyId) {
		List<Notice> list = noticeService.findNoticeByStudyId(studyId);
		return ResponseEntity.status(200).body(list);
	}

	@GetMapping("/notice/teacher/list/{tchrId}")
	@ApiOperation(value = "교사가 출제한 공지사항 조회", notes = "<strong>교사아이디</strong>를 통해 조회 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<Notice>> tchr_list(
			@PathVariable @ApiParam(value = "공지사항 정보", required = true)String tchrId){
		List<Notice> list = noticeService.findNoticeByTchrId(tchrId);
		return ResponseEntity.status(200).body(list);
	}

	@GetMapping("/notice/student/list/{stId}")
	@ApiOperation(value = "학생이 속한 수업의 과제 조회", notes = "<strong>학생아이디</strong>를 통해 조회 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<Notice>> student_list(
			@PathVariable @ApiParam(value = "학생ID", required = true) String stId) {
		List<Notice> list = noticeService.findNoticeBystId(stId);
		return ResponseEntity.status(200).body(list);
	}
}
