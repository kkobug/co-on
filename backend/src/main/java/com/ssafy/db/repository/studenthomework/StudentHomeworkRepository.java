package com.ssafy.db.repository.studenthomework;

import com.ssafy.db.entity.StudentHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StudentHomeworkRepository extends JpaRepository<StudentHomework, Integer> {
    Optional<StudentHomework> findBystHwId(Integer StHwId);
    @Transactional
    void deleteStudentHomeworkByStHwIdAndStId(Integer stHwId, String stId);

    @Modifying
    @Query(value = "update studyroom_detail set st_point = st_point + :point where study_id = :studyId and st_id = :st",nativeQuery = true)
    void updatePoint(int point,int studyId, String st);
}
