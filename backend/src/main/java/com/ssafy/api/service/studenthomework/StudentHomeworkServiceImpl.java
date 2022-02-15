package com.ssafy.api.service.studenthomework;

import com.ssafy.api.request.studenthomework.StudentHomeworkPutReq;
import com.ssafy.api.request.studenthomework.StudentHomeworkRegisterPostReq;
import com.ssafy.api.request.studenthomework.StudentHomeworkUpdatePutReq;
import com.ssafy.db.entity.StudentHomework;
import com.ssafy.db.entity.StudentHomeworkFile;
import com.ssafy.db.repository.studenthomework.StudentHomeworkFileRepository;
import com.ssafy.db.repository.studenthomework.StudentHomeworkRepository;
import com.ssafy.db.repository.studenthomework.StudentHomeworkRepositorySupport;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service("StudentHomeworkService")
public class StudentHomeworkServiceImpl implements StudentHomeworkService{
    @Autowired
    StudentHomeworkRepository studenthomeworkRepository;

    @Autowired
    StudentHomeworkFileRepository studentHomeworkFileRepository;

    @Autowired
    StudentHomeworkRepositorySupport studenthomeworkRepositorySupport;

    @Override
    @CachePut(value = "findSubmittedHw",key = "#studentHomeworkRegisterPostReq.stId")
    public StudentHomework createStudentHomework(StudentHomeworkRegisterPostReq studentHomeworkRegisterPostReq) {
        StudentHomework studenthomework = studenthomeworkRepositorySupport.findByIds(studentHomeworkRegisterPostReq.getStId(), studentHomeworkRegisterPostReq.getHwId());
        studenthomework.setStHwcontent(studentHomeworkRegisterPostReq.getStHwContent());
        studenthomework.setStHwposted(LocalDateTime.now());
        System.out.println("!!!!!!!!!!!!!createStu service in!!!!!!!!!!!!!");
        System.out.println(studentHomeworkRegisterPostReq.getStHwId());
        studentHomeworkFileRepository.deleteStudentHomeworkFileByStHwId(studentHomeworkRegisterPostReq.getStHwId());
        if (!studentHomeworkRegisterPostReq.getStHwFile().get(0).isEmpty()){
            List<MultipartFile> sthwFile = studentHomeworkRegisterPostReq.getStHwFile();
            for (MultipartFile multipartFile : sthwFile) {
                StudentHomeworkFile newFile = new StudentHomeworkFile();
                newFile.setStHwId(studenthomework.getStHwId());

                String sourceFileName = multipartFile.getOriginalFilename();
                File destinationNoticeFile;
                String destinationNoticeFileName;
                String noticePath = "./assets/homework/student_homework/";

                destinationNoticeFileName = RandomStringUtils.randomAlphanumeric(8) + sourceFileName;
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
        studenthomeworkRepository.save(studenthomework);
        return studenthomework;
    }

    @Override
    @CacheEvict(value = "findSubmittedHw", key = "#stId")
    public void deleteStudentHomework(Integer hwId, String stId) {
        studenthomeworkRepositorySupport.deleteStudentHomeworkByStHwIdAndStId(hwId, stId);
    }

//    @Override
//    public StudentHomework updateStudentHomework(Integer stHwId, StudentHomeworkUpdatePutReq StudentHomeworkUpdatePutReq) {
//        StudentHomework studenthomework = new StudentHomework();
//        studenthomework.setStHwId(StudentHomeworkUpdatePutReq.getStHwId());
//        studenthomework.setStHwcontent(StudentHomeworkUpdatePutReq.getStHwContent());
//        studenthomework.setStHwposted(LocalDateTime.now());
//        if (!Objects.equals(StudentHomeworkUpdatePutReq.getStHwId(), stHwId)) return studenthomework;
//        studenthomeworkRepository.save(studenthomework);
//        studentHomeworkFileRepository.deleteStudentHomeworkFileByStHwId(stHwId);
//        if (!StudentHomeworkUpdatePutReq.getStHwFile().get(0).isEmpty()){
//            List<MultipartFile> sthwFile = StudentHomeworkUpdatePutReq.getStHwFile();
//            for (MultipartFile multipartFile : sthwFile) {
//                StudentHomeworkFile newFile = new StudentHomeworkFile();
//                newFile.setStHwId(studenthomework.getStHwId());
//
//                String sourceFileName = multipartFile.getOriginalFilename();
//                File destinationNoticeFile;
//                String destinationNoticeFileName;
//                String noticePath = "D:/";
//                LocalDateTime nowtime = LocalDateTime.now();
//
//                destinationNoticeFileName = nowtime + RandomStringUtils.randomAlphanumeric(8) + sourceFileName;
//                destinationNoticeFile = new File(noticePath + destinationNoticeFileName);
//
//                destinationNoticeFile.getParentFile().mkdirs();
//                try {
//                    multipartFile.transferTo(destinationNoticeFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                newFile.setFileName(destinationNoticeFileName);
//                newFile.setFileOriginName(sourceFileName);
//                newFile.setFilePath(noticePath);
//                studentHomeworkFileRepository.save(newFile);
//
//            }
//        }
//        return studenthomework;
//    }

    //과제에 포함된 학생 과제 조회
    @Override
    public List<StudentHomework> findStudentHomeworkByHwId(Integer hwId) {
        return studenthomeworkRepositorySupport.findStudentHomeworkByHwId(hwId);
    }

    //학생이 제출한 과제 조회 -> 과제 제출, 삭제시 캐시 업데이트
    @Override
    @Cacheable(value = "findSubmittedHw",key = "#stId")
    public List<StudentHomework> findStudentHomeworkByStId(String stId) {
        System.out.println("findStudentHomeworkByStId................"+stId);
        return studenthomeworkRepositorySupport.findStudentHomeworkByStId(stId);
    }

    //학생 과제 세부 조회 10분 지나면 다시 캐시
    @Override
    @Cacheable(value = "findStHw")
    public StudentHomework findBystHwId(Integer stHwId) {
        StudentHomework studentHomework = studenthomeworkRepositorySupport.findBystHwId(stHwId).get();
        System.out.println("findBystHwId................"+stHwId);
        return studentHomework;
    }

    @Override
    @CacheEvict(value = "findStudent")
    public void updateScore(StudentHomeworkPutReq studentHomeworkPutReq) {
        int stHwId = studentHomeworkPutReq.getStHwId();
        int stScore = studentHomeworkPutReq.getStHwscore();
        int chgScore = studentHomeworkPutReq.getChgstHwscore();
        StudentHomework studentHomework = findBystHwId(stHwId);
        int num = chgScore-stScore; //기존 점수와 채점점수 차이
        int studyId = studentHomework.getStudyId();
        String s = String.valueOf(studentHomework.getStHwscore());
        int score = num;
        if(s != "null" || s != "0"){ //null인 경우 nullpointer
            score += studentHomework.getStHwscore();
        }
        studentHomework.setStHwscore(score);
        studenthomeworkRepository.save(studentHomework);
        String st = studentHomework.getStId();
        studenthomeworkRepository.updatePoint(num, studyId,st);
    }

    @Override
    public Resource loadAsResource(String fileName, String filePath) {
        try {
            System.out.println("loadAsResource run!!!!!!!!!!!!!!");
            Path file = Paths.get(filePath).resolve(fileName);
            System.out.println(file);
            System.out.println("file run!!!!!!!!!!!!!!");
            System.out.println(file.toUri());
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
