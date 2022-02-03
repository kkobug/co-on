package com.ssafy.api.service;

import com.ssafy.api.request.HomeworkModifyReq;
import com.ssafy.api.request.HomeworkRegisterPostReq;
import com.ssafy.api.request.StudentUpdatePutReq;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.Student;
import com.ssafy.db.repository.HomeworkRepository;
import com.ssafy.db.repository.HomeworkRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService{
    @Autowired
    HomeworkRepository homeworkRepository;

    @Autowired
    HomeworkRepositorySupport homeworkRepositorySupport;

    @Override
    public Homework createHomework(HomeworkRegisterPostReq homeworkRegisterPostReq) {
        Homework homework = new Homework();
        homework.setHwTitle(homeworkRegisterPostReq.getHwTitle());
        homework.setHwContent(homeworkRegisterPostReq.getHwContent());
        homework.setHwDeadline(homeworkRegisterPostReq.getHwDeadline());
        homework.setTchrId(homeworkRegisterPostReq.getTchrId());
        homework.setStudyId(homeworkRegisterPostReq.getStudyId());
        return homeworkRepository.save(homework);
    }

    @Override
    public void deleteHomework(int hwId, String tchrId) {
        homeworkRepositorySupport.deleteHomeworkByHwId(hwId, tchrId);
    }

    @Override
    public List<int[]> countBystId(String stId) {
        return homeworkRepository.countBystId(stId);
    }

    @Override
    public List<Homework> findHomeworkBystId(String stId) {
        return homeworkRepository.findHomeworkBystId(stId);
    }

    @Override
    public List<Homework> findHomeworkByStudyId(Integer studyId) {
        return homeworkRepositorySupport.findHomeworkByStudyId(studyId);
    }

    @Override
    public List<Homework> findHomeworkByTchrId(String tchrId) {
        return homeworkRepositorySupport.findHomeworkByTchrId(tchrId);
    }

    @Override
    public Homework findHomeworkByHwId(Integer hwId) {
        Homework hw = homeworkRepositorySupport.findHomeworkByHwId(hwId).get();
        return hw;
    }

    @Override
    public Homework updateHomework(Integer hwId, HomeworkModifyReq homeworkModifyReq) {
        Homework homework = new Homework();
        homework.setHwId(homeworkModifyReq.getHwId());
        homework.setHwTitle(homeworkModifyReq.getHwTitle());
        homework.setHwContent(homeworkModifyReq.getHwContent());
        homework.setHwDeadline(homeworkModifyReq.getHwDeadline());
//        homework.setTchrId(homeworkRegisterPostReq.getTchrId());
//        homework.setStudyId(homeworkRegisterPostReq.getStudyId());
        homework.setHwPosted(LocalDateTime.now());
        if (!Objects.equals(homeworkModifyReq.getHwId(), hwId)) return homework;
        return homeworkRepository.save(homework);
    }
}
