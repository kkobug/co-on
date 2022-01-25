package com.ssafy.db.repository;

import com.ssafy.db.entity.Student;
import com.ssafy.db.entity.Studyroom;
import com.ssafy.db.entity.StudyroomDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudyRoomDetailRepository extends JpaRepository<StudyroomDetail, Integer> {
    //수업에 추가하기 위해 학생(아이디와 이름 조회
}
