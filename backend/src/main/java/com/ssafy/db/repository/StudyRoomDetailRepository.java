package com.ssafy.db.repository;

import com.ssafy.db.entity.Student;
import com.ssafy.db.entity.Studyroom;
import com.ssafy.db.entity.StudyroomDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudyRoomDetailRepository extends JpaRepository<StudyroomDetail, Integer> {

    @Query(value = "select student.st_id, student.st_name, student.st_img from student where student.st_id in " +
            "(select st_id from studyroom_detail where study_id = :studyId)", nativeQuery = true)
    List<Object[]> findstIdAndstNameBystudyId(int studyId);

//    @Modifying //삭제 시 executeUpdate
//    @Query(value = "delete studyroom_detail where study_id = :studyId and st_id = :stId",nativeQuery = true)
//    void deleteBystIdAndstudyId(int studyId, String stId);
}
