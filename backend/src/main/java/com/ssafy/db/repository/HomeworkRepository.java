package com.ssafy.db.repository;

import com.ssafy.db.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Integer> {
//    List<Homework> findHomeworkByStudyId (Integer studyId);
}
