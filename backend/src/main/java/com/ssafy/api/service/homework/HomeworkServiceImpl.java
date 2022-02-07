package com.ssafy.api.service.homework;

import com.ssafy.api.request.homework.HomeworkModifyReq;
import com.ssafy.api.request.homework.HomeworkRegisterPostReq;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.HomeworkFile;
import com.ssafy.db.repository.homework.HomeworkFileRepository;
import com.ssafy.db.repository.homework.HomeworkRepository;
import com.ssafy.db.repository.homework.HomeworkRepositorySupport;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService{
    @Autowired
    HomeworkRepository homeworkRepository;

    @Autowired
    HomeworkFileRepository homeworkFileRepository;

    @Autowired
    HomeworkRepositorySupport homeworkRepositorySupport;

    @Override
    public Homework createHomework(HomeworkRegisterPostReq homeworkRegisterPostReq) {
        Homework homework = new Homework();
        System.out.println("어디가문제니 1111111");
        System.out.println(homeworkRegisterPostReq.getHwTitle());
        homework.setHwTitle(homeworkRegisterPostReq.getHwTitle());
        System.out.println("어디가문제니 2222222");
        System.out.println(homeworkRegisterPostReq.getHwContent());
        homework.setHwContent(homeworkRegisterPostReq.getHwContent());
        System.out.println("어디가문제니 3333333");
        System.out.println(homeworkRegisterPostReq.getHwDeadline());
        homework.setHwDeadline(homeworkRegisterPostReq.getHwDeadline());
        System.out.println("어디가문제니 4444444");
        System.out.println(homeworkRegisterPostReq.getTchrId());
        homework.setTchrId(homeworkRegisterPostReq.getTchrId());
        System.out.println("어디가문제니 5555555");
        System.out.println(homeworkRegisterPostReq.getStudyId());
        homework.setStudyId(homeworkRegisterPostReq.getStudyId());
        System.out.println("어디가문제니 6666666");
        homeworkRepository.save(homework);
        if (!homeworkRegisterPostReq.getHwFile().get(0).isEmpty()) {
            List<MultipartFile> hwFile = homeworkRegisterPostReq.getHwFile();
            for (MultipartFile multipartFile : hwFile) {
                HomeworkFile newFile = new HomeworkFile();
                newFile.setHwId(homework.getHwId());

                String sourceFileName = multipartFile.getOriginalFilename();
                File destinationHomeworkFile;
                String destinationHomeworkFileName;
                String homeworkPath = "D:/";

                destinationHomeworkFileName = "stu" + RandomStringUtils.randomAlphanumeric(8) + sourceFileName;
                destinationHomeworkFile = new File(homeworkPath + destinationHomeworkFileName);

                destinationHomeworkFile.getParentFile().mkdirs();
                try {
                    multipartFile.transferTo(destinationHomeworkFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                newFile.setFileName(destinationHomeworkFileName);
                newFile.setFileOriginName(sourceFileName);
                newFile.setFilePath(homeworkPath);
                homeworkFileRepository.save(newFile);
            }
        }
        return homework;
    }

    @Override
    public void deleteHomework(Integer hwId, String tchrId) {
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
    public Homework updateHomework(HomeworkModifyReq homeworkModifyReq) {
        Homework homework = homeworkRepositorySupport.findHomeworkByHwId(homeworkModifyReq.getHwId()).get();
        homework.setHwId(homeworkModifyReq.getHwId());
        homework.setHwTitle(homeworkModifyReq.getHwTitle());
        homework.setHwContent(homeworkModifyReq.getHwContent());
        homework.setHwDeadline(homeworkModifyReq.getHwDeadline());
        homework.setHwPosted(LocalDateTime.now());
        homeworkRepository.save(homework);
        homeworkFileRepository.deleteHomeworkFileByHwId(homeworkModifyReq.getHwId());
        if (!homeworkModifyReq.getHwFile().get(0).isEmpty()) {
            List<MultipartFile> hwFile = homeworkModifyReq.getHwFile();
            for (MultipartFile multipartFile : hwFile) {
                HomeworkFile newFile = new HomeworkFile();
                newFile.setHwId(homework.getHwId());

                String sourceFileName = multipartFile.getOriginalFilename();
                File destinationHomeworkFile;
                String destinationHomeworkFileName;
                String homeworkPath = "D:/";

                destinationHomeworkFileName = "stu" + RandomStringUtils.randomAlphanumeric(8) + sourceFileName;
                destinationHomeworkFile = new File(homeworkPath + destinationHomeworkFileName);

                destinationHomeworkFile.getParentFile().mkdirs();
                try {
                    multipartFile.transferTo(destinationHomeworkFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                newFile.setFileName(destinationHomeworkFileName);
                newFile.setFileOriginName(sourceFileName);
                newFile.setFilePath(homeworkPath);
                homeworkFileRepository.save(newFile);
            }
        }

        return homework;
    }
}
