package com.ssafy.db.repository.notice;

import com.ssafy.db.entity.NoticeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface NoticeFileRepository extends JpaRepository<NoticeFile, Long> {

    // 공지 ID로 모든 파일 조회
    @Query(value = "select notice_file.* from notice_file where notice_file.notice_id = :noticeId"
            , nativeQuery = true)
    List<MultipartFile> findNoticeFileBynoticeId(Integer noticeId);

    // 파일 삭제
    @Transactional
    @Modifying
    @Query(value = "delete from notice_file where notice_file.notice_id = :noticeId", nativeQuery = true)
    void deleteNoticeFileByNoticeId(Integer noticeId);
}
