package com.ssafy.db.repository.studyroom;

import com.ssafy.db.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudyRoomDetailRepository extends JpaRepository<StudyroomDetail, Integer> {
    //@Query(value = "select d.study_id AS study_id, d.st_id AS st_id, s.student AS st_name from studyroom_detail AS d inner join student AS s on d.st_id=s.st_id where d.study_id= :studyId", nativeQuery = true)
    @Query(value = "select d.study_id AS study_id, d.st_id AS st_id, s.st_name AS st_name,s.st_prof_name AS st_prof_name, s.st_origin_prof_name AS st_origin_prof_name, s.st_prof_path ASt_prof_p sath" +
            "from studyroom_detail AS d " +
            "inner join student AS s on d.st_id=s.st_id where d.study_id= :studyId", nativeQuery = true)
    List<Object[]> findStudyroombystudyId(int studyId);

@Query(value = "select st.study_id, st.tchr_id, st.study_name, st.study_desc, c.conf_id, c.conf_title, date_format(c.conf_init, '%Y-%m-%d %H:%i:%s'), date_format(c.conf_des, '%Y-%m-%d %H:%i:%s'), date_format(c.conf_start, '%Y-%m-%d %H:%i:%s'), date_format(c.conf_end, '%Y-%m-%d %H:%i:%s'), date_format(c.conf_att, '%Y-%m-%d %H:%i:%s'), sd.st_point\n" +
            "from studyroom as st, conference as c\n" +
            "left join studyroom_detail as sd\n" +
            "on sd.study_id = c.study_id\n" +
            "where sd.st_id = :stId and (st.study_id, st.tchr_id, c.conf_start) in (select conf.study_id, conf.tchr_id, max(conf_start)\n"+
            "from conference as conf\n"+
            "group by conf.study_id)",nativeQuery = true)
    List<Object[]> findStudyroomAndconbystId(String stId);
    //    @Query(value = "select st.study_id, st.tchr_id, st.study_name, st.study_desc, c.conf_id, c.conf_title, c.conf_init, c.conf_des, c.conf_start, c.conf_end, c.conf_att\n" +
//            "from studyroom as st\n" +

//            "left join studyroom_detail as sd\n" +
//            "on st.study_id = sd.study_id\n" +
//            "left join conference as c\n" +
//            "on st.study_id = c.study_id and st.tchr_id = c.tchr_id\n" +
//            "where sd.st_id = :stId\n"+
//    List<Object[]> findStudyroomAndconbystId(String stId);

    @Query(value = "select s.st_id, s.st_name, s.st_email, s.st_contact, s.st_school, s.st_birthday,studyroom_detail.st_point,s.st_prof_name, s.st_origin_prof_name, s.st_prof_path \n" +
            "from student as s\n" +
            "left join studyroom_detail\n" +
            "on s.st_id = studyroom_detail.st_id\n" +
            "where studyroom_detail.study_id = :studyId", nativeQuery = true)
    List<Object[]> findstudentbystudyId(int studyId);

    @Transactional
    @Modifying
    @Query(value = "update studyroom_detail set st_point = st_point + :score where study_id = :studyId and st_id = :stId",nativeQuery = true)
    void updateScore(int score, int studyId, String stId);
}
