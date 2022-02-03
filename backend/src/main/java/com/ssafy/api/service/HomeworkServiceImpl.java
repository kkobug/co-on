package com.ssafy.api.service;

import com.ssafy.api.request.HomeworkModifyReq;
import com.ssafy.api.request.HomeworkRegisterPostReq;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.HomeworkFile;
import com.ssafy.db.repository.HomeworkFileRepository;
import com.ssafy.db.repository.HomeworkRepository;
import com.ssafy.db.repository.HomeworkRepositorySupport;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
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
        homework.setHwTitle(homeworkRegisterPostReq.getHwTitle());
        homework.setHwContent(homeworkRegisterPostReq.getHwContent());
        homework.setHwDeadline(homeworkRegisterPostReq.getHwDeadline());
        homework.setTchrId(homeworkRegisterPostReq.getTchrId());
        homework.setStudyId(homeworkRegisterPostReq.getStudyId());
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
                LocalDateTime nowtime = LocalDateTime.now();

                destinationHomeworkFileName = nowtime + RandomStringUtils.randomAlphanumeric(8) + sourceFileName;
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
        Homework homework = homeworkRepositorySupport.findHomeworkByHwId(hwId).get();
        homework.setHwId(homeworkModifyReq.getHwId());
        homework.setHwTitle(homeworkModifyReq.getHwTitle());
        homework.setHwContent(homeworkModifyReq.getHwContent());
        homework.setHwDeadline(homeworkModifyReq.getHwDeadline());
        homework.setHwPosted(LocalDateTime.now());
        if (!Objects.equals(homeworkModifyReq.getHwId(), hwId)) return homework;
        homeworkRepository.save(homework);
        homeworkFileRepository.deleteHomeworkFileByHwId(hwId);
        if (!homeworkModifyReq.getHwFile().get(0).isEmpty()) {
            List<MultipartFile> hwFile = homeworkModifyReq.getHwFile();
            for (MultipartFile multipartFile : hwFile) {
                HomeworkFile newFile = new HomeworkFile();
                newFile.setHwId(homework.getHwId());

                String sourceFileName = multipartFile.getOriginalFilename();
                File destinationHomeworkFile;
                String destinationHomeworkFileName;
                String homeworkPath = "D:/";
                LocalDateTime nowtime = LocalDateTime.now();

                destinationHomeworkFileName = nowtime + RandomStringUtils.randomAlphanumeric(8) + sourceFileName;
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
