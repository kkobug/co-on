package com.ssafy.db.repository.homework;

import com.ssafy.db.entity.HomeworkFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface HomeworkFileRepository extends JpaRepository<HomeworkFile, Integer> {

    // 공지 ID로 모든 파일 조회
    @Query(value = "select homework_file.* from homework_file where homework_file.hw_id = :hwId"
            , nativeQuery = true)
    List<MultipartFile> findHomeworkFileByHwId(Integer hwId);

    // 파일 삭제
    @Transactional
    @Modifying
    @Query(value = "delete from homework_file where homework_file.hw_id = :hwId", nativeQuery = true)
    void deleteHomeworkFileByHwId(Integer hwId);
}
