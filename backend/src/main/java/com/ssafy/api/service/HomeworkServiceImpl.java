package com.ssafy.api.service;

import com.ssafy.api.request.HomeworkRegisterPostReq;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.repository.HomeworkRepository;
import com.ssafy.db.repository.HomeworkRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void deleteHomework(Integer studyId) {

    }

    @Override
    public List<String> findHomeworkByStudyId(Integer studyId) {
        return homeworkRepositorySupport.findHomeworkByStudyId(studyId);
    }

    @Override
    public List<String> findHomeworkByTchrId(String tchrId) {
        return homeworkRepositorySupport.findHomeworkByTchrId(tchrId);
    }
}
