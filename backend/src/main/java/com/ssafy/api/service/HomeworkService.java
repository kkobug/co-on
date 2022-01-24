package com.ssafy.api.service;

import com.ssafy.api.request.HomeworkRegisterPostReq;
import com.ssafy.db.entity.Homework;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HomeworkService {
//    과제 등록
    Homework createHomework(HomeworkRegisterPostReq homeworkRegisterPostReq);
//    과제 삭제
    @Transactional
    void deleteHomework(Integer hwId);
//    과제 조회 (수업ID에 따라, 교사ID에 따라)
    List<String> findHomeworkByStudyId(Integer studyId);
    List<String> findHomeworkByTchrId(String tchrId);
}
