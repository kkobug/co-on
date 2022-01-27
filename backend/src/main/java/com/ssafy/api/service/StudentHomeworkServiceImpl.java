package com.ssafy.api.service;

import com.ssafy.api.request.HomeworkModifyReq;
import com.ssafy.api.request.HomeworkRegisterPostReq;
import com.ssafy.api.request.StudentHomeworkRegisterPostReq;
import com.ssafy.api.request.StudentHomeworkUpdatePutReq;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.StudentHomework;
import com.ssafy.db.repository.HomeworkRepository;
import com.ssafy.db.repository.HomeworkRepositorySupport;
import com.ssafy.db.repository.StudentHomeworkRepository;
import com.ssafy.db.repository.StudentHomeworkRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service("StudentHomeworkService")
public class StudentHomeworkServiceImpl implements StudentHomeworkService{
    @Autowired
    StudentHomeworkRepository studenthomeworkRepository;

    @Autowired
    StudentHomeworkRepositorySupport studenthomeworkRepositorySupport;

    @Override
    public StudentHomework createStudentHomework(StudentHomeworkRegisterPostReq studentHomeworkRegisterPostReq) {
        StudentHomework studenthomework = new StudentHomework();
        studenthomework.setHwId(studentHomeworkRegisterPostReq.getHwId());
        studenthomework.setStudyId(studentHomeworkRegisterPostReq.getStudyId());
        studenthomework.setTchrId(studentHomeworkRegisterPostReq.getTchrId());
        studenthomework.setStId(studentHomeworkRegisterPostReq.getStId());
        studenthomework.setStHwcontent(studentHomeworkRegisterPostReq.getStHwContent());
        return studenthomeworkRepository.save(studenthomework);
    }

    @Override
    public void deleteStudentHomework(int stHwId, String stId) {
        studenthomeworkRepositorySupport.deleteStudentHomeworkByStHwIdAndStId(stHwId, stId);
    }

    @Override
    public StudentHomework StudentHomeworkupdateNotice(int stHwId, StudentHomeworkUpdatePutReq StudentHomeworkUpdatePutReq) {
        StudentHomework studenthomework = new StudentHomework();
        studenthomework.setStHwId(StudentHomeworkUpdatePutReq.getStHwId());
//        studenthomework.setHwId(StudentHomeworkUpdatePutReq.getHwId());
//        studenthomework.setStudyId(StudentHomeworkUpdatePutReq.getStudyId());
//        studenthomework.setTchrId(StudentHomeworkUpdatePutReq.getTchrId());
//        studenthomework.setStId(StudentHomeworkUpdatePutReq.getStId());
        studenthomework.setStHwcontent(StudentHomeworkUpdatePutReq.getStHwContent());
        studenthomework.setStHwposted(LocalDateTime.now());
        if (!Objects.equals(StudentHomeworkUpdatePutReq.getStHwId(), stHwId)) return studenthomework;
        return studenthomeworkRepository.save(studenthomework);
    }

    @Override
    public List<StudentHomework> findStudentHomeworkByHwId(int hwId) {
        return studenthomeworkRepositorySupport.findStudentHomeworkByHwId(hwId);
    }

    @Override
    public List<StudentHomework> findStudentHomeworkByStId(String stId) {
        return studenthomeworkRepositorySupport.findStudentHomeworkByStId(stId);
    }

    @Override
    public StudentHomework findBystHwId(int stHwId) {
        StudentHomework studentHomework = studenthomeworkRepositorySupport.findBystHwId(stHwId).get();
        return studentHomework;
    }
}
