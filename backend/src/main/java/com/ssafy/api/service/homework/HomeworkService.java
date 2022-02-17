package com.ssafy.api.service.homework;

import com.ssafy.api.request.homework.HomeworkModifyReq;
import com.ssafy.api.request.homework.HomeworkRegisterPostReq;
import com.ssafy.db.entity.Homework;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public interface HomeworkService {
    //    과제 등록
    Homework createHomework(HomeworkRegisterPostReq homeworkRegisterPostReq) throws IOException;

    //    과제 조회 (수업ID에 따라, 교사ID에 따라, 과제 ID에 따라)
    List<Homework> findHomeworkByStudyId(Integer studyId);

    List<Homework> findHomeworkByTchrId(String tchrId);

    List<Homework> findHomeworkBystId(String stId);

    Homework findHomeworkByHwId(Integer hwId);

    //    과제 수정
    Homework updateHomework(HomeworkModifyReq homeworkModifyReq) throws IOException;

    //    과제 삭제
    @Transactional
    void deleteHomework(Integer hwId, String tchrId);

    int[] countBystId(String stId);

    List<int[]> countBytchrId(String tchrId);

    URL loadAsResource(String fileName);
}
