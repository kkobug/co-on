package com.ssafy.db.repository;

import com.ssafy.db.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Integer> {
//    List<Homework> findHomeworkByStudyId (Integer studyId);
//    List<Homework> findHomeworkByTchrId(String tchrId);
//    Optional<Homework> findHomeworkByHwId(Integer hwId);
//    void deleteHomeworkByHwId(Integer hwId);

    @Query(value = "select homework.* from homework where homework.study_id in " +
            "( select study_id from studyroom_detail where st_id = :stId)",nativeQuery = true)
    List<Homework> findHomeworkBystId(String stId);

}
