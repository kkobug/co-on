package com.ssafy.db.repository;

import com.ssafy.db.entity.HomeworkFile;
import com.ssafy.db.entity.NoticeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface HomeworkFileRepository extends JpaRepository<HomeworkFile, Long> {

    // 공지 ID로 모든 파일 조회
    @Query(value = "SELECT HW_FILE.* FROM HW_FILE WHERE HW_FILE.hw_id = :hwId"
            , nativeQuery = true)
    List<MultipartFile> findHomeworkFileByHwId(long hwId);

    // 파일 삭제
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM HW_FILE WHERE HW_FILE.hw_id = :hwId", nativeQuery = true)
    void deleteHomeworkFileByHwId(long hwId);
}
