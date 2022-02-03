package com.ssafy.api.service;

import com.ssafy.api.request.HomeworkModifyReq;
import com.ssafy.api.request.HomeworkRegisterPostReq;
import com.ssafy.api.request.StudentHomeworkRegisterPostReq;
import com.ssafy.api.request.StudentHomeworkUpdatePutReq;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.StudentHomework;
import com.ssafy.db.entity.StudentHomeworkFile;
import com.ssafy.db.repository.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service("StudentHomeworkService")
public class StudentHomeworkServiceImpl implements StudentHomeworkService{
    @Autowired
    StudentHomeworkRepository studenthomeworkRepository;

    @Autowired
    StudentHomeworkFileRepository studentHomeworkFileRepository;

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
        studenthomeworkRepository.save(studenthomework);
        if (!studentHomeworkRegisterPostReq.getStHwFile().get(0).isEmpty()){
            List<MultipartFile> sthwFile = studentHomeworkRegisterPostReq.getStHwFile();
            for (MultipartFile multipartFile : sthwFile) {
                StudentHomeworkFile newFile = new StudentHomeworkFile();
                newFile.setStHwId(studenthomework.getStHwId());

                String sourceFileName = multipartFile.getOriginalFilename();
                File destinationNoticeFile;
                String destinationNoticeFileName;
                String noticePath = "D:/";
                LocalDateTime nowtime = LocalDateTime.now();

                destinationNoticeFileName = nowtime + RandomStringUtils.randomAlphanumeric(8) + sourceFileName;
                destinationNoticeFile = new File(noticePath + destinationNoticeFileName);

                destinationNoticeFile.getParentFile().mkdirs();
                try {
                    multipartFile.transferTo(destinationNoticeFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                newFile.setFileName(destinationNoticeFileName);
                newFile.setFileOriginName(sourceFileName);
                newFile.setFilePath(noticePath);
                studentHomeworkFileRepository.save(newFile);

            }
        }
        return studenthomework;
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
        studenthomeworkRepository.save(studenthomework);
        studentHomeworkFileRepository.deleteStudentHomeworkFileByStHwId(stHwId);
        if (!StudentHomeworkUpdatePutReq.getStHwFile().get(0).isEmpty()){
            List<MultipartFile> sthwFile = StudentHomeworkUpdatePutReq.getStHwFile();
            for (MultipartFile multipartFile : sthwFile) {
                StudentHomeworkFile newFile = new StudentHomeworkFile();
                newFile.setStHwId(studenthomework.getStHwId());

                String sourceFileName = multipartFile.getOriginalFilename();
                File destinationNoticeFile;
                String destinationNoticeFileName;
                String noticePath = "D:/";
                LocalDateTime nowtime = LocalDateTime.now();

                destinationNoticeFileName = nowtime + RandomStringUtils.randomAlphanumeric(8) + sourceFileName;
                destinationNoticeFile = new File(noticePath + destinationNoticeFileName);

                destinationNoticeFile.getParentFile().mkdirs();
                try {
                    multipartFile.transferTo(destinationNoticeFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                newFile.setFileName(destinationNoticeFileName);
                newFile.setFileOriginName(sourceFileName);
                newFile.setFilePath(noticePath);
                studentHomeworkFileRepository.save(newFile);

            }
        }
        return studenthomework;
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
