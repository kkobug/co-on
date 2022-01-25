package com.ssafy.db.repository;

import com.ssafy.db.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Optional<Notice> findByNoticeId(int NoticeId);
    @Transactional
    void deleteNoticeByNoticeIdAndTchrId(int noticeId, String tchrId);
}
