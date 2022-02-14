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
    @Query(value = "select n.*\n" +
            "from notice as n\n" +
            "left join studyroom_detail as sd \n" +
            "on n.study_id = sd.study_id\n" +
            "left join studyroom as s\n" +
            "on n.study_id = s.study_id\n" +
            "where sd.st_id = :stId\n" +
            "order by n.notice_posted desc", nativeQuery = true)
    List<Notice> findNoticeBystId(String stId);

}
