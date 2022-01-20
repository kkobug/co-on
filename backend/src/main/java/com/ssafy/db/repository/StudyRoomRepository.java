package com.ssafy.db.repository;

import com.ssafy.db.entity.Studyroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyRoomRepository  extends JpaRepository<Studyroom, Integer> {
    //List<Studyroom> findStudyNameByTchrId(String tchrId);
    @Transactional
    void deleteStudyroomByTchrIdAndStudyName(String tchrId, String studyName);
}
