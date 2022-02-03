package com.ssafy.db.repository.studyroom;

import com.ssafy.db.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudyRoomDetailRepository extends JpaRepository<StudyroomDetail, Integer> {
    //@Query(value = "select d.study_id AS study_id, d.st_id AS st_id, s.student AS st_name from studyroom_detail AS d inner join student AS s on d.st_id=s.st_id where d.study_id= :studyId", nativeQuery = true)
    @Query(value = "select d.study_id AS study_id, d.st_id AS st_id, s.st_name AS st_name from studyroom_detail AS d inner join student AS s on d.st_id=s.st_id where d.study_id= :studyId", nativeQuery = true)
    List<Object[]> findStudyroombystudyId(int studyId);
}
