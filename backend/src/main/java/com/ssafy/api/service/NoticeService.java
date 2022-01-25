package com.ssafy.api.service;

import com.ssafy.api.request.NoticeUpdatePutReq;
import com.ssafy.db.entity.Notice;
import com.ssafy.api.request.NoticeRegisterPostReq;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface NoticeService {
	// 공지사항 등록
	Notice createNotice(NoticeRegisterPostReq noticeRegisterPostReq);
	// 공지사항 삭제(공지사항 ID와 교수 ID로 삭제)
	@Transactional
	void deleteNotice(int noticeId, String tchrId);
	// 공지사항 상세 조회
	Notice findBynoticeId(int NoticeId);
	Notice updateNotice(NoticeUpdatePutReq noticeUpdatePutReq);

	@Transactional
	List<Notice> getAllNotices();
}
