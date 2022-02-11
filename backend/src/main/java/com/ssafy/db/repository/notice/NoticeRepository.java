package com.ssafy.db.repository.notice;

import com.ssafy.db.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    // 학생 ID로 모든 공지 조회 (학생이 속해있는 모든 수업에서 발생한 공지 조회)
    @Query(value = "select notice.* from notice where notice.study_id in " +
            "(select study_id from studyroom_detail where st_id = :stId) " +
            "order by notice.notice_posted desc", nativeQuery = true)
    List<Notice> findNoticeBystId(String stId);

}
