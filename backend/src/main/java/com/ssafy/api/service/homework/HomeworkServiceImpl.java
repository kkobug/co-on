package com.ssafy.api.service.homework;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.api.request.homework.HomeworkModifyReq;
import com.ssafy.api.request.homework.HomeworkRegisterPostReq;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.HomeworkFile;
import com.ssafy.db.entity.StudentHomework;
import com.ssafy.db.repository.homework.HomeworkFileRepository;
import com.ssafy.db.repository.homework.HomeworkRepository;
import com.ssafy.db.repository.homework.HomeworkRepositorySupport;
import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public Homework createHomework(HomeworkRegisterPostReq homeworkRegisterPostReq) throws IOException{
        Homework homework = new Homework();
        String[] dl = homeworkRegisterPostReq.getHwDeadline().split("[/ :]");
        LocalDateTime deadline = LocalDateTime.of(
                Integer.parseInt(dl[0]),
                Integer.parseInt(dl[1]),
                Integer.parseInt(dl[2]),
                Integer.parseInt(dl[3]),
                Integer.parseInt(dl[4]),
                Integer.parseInt(dl[5]));
        homework.setHwTitle(homeworkRegisterPostReq.getHwTitle());
        homework.setHwContent(homeworkRegisterPostReq.getHwContent());
        homework.setHwDeadline(deadline);
        homework.setTchrId(homeworkRegisterPostReq.getTchrId());
        homework.setStudyId(homeworkRegisterPostReq.getStudyId());
        homeworkRepository.save(homework);
        if (!homeworkRegisterPostReq.getHwFile().get(0).isEmpty()) {
            List<MultipartFile> hwFile = homeworkRegisterPostReq.getHwFile();
            for (MultipartFile multipartFile : hwFile) {
                LocalDateTime now = LocalDateTime.now();
                HomeworkFile newFile = new HomeworkFile();
                newFile.setHwId(homework.getHwId());

                String sourceFileName = multipartFile.getOriginalFilename();
//                File destinationHomeworkFile;
                String destinationHomeworkFileName;
//                String homeworkPath = "./assets/homework/teacher_homework/";
//
                String today = now.format(DateTimeFormatter.ofPattern("MMddHHmmssSSS"));
                destinationHomeworkFileName = "HW" + today + sourceFileName;

                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(multipartFile.getSize());
                objectMetadata.setContentType(multipartFile.getContentType());
                amazonS3.putObject(new PutObjectRequest(bucket, destinationHomeworkFileName, multipartFile.getInputStream(), objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
//                destinationHomeworkFile = new File(homeworkPath + destinationHomeworkFileName);
//
//                destinationHomeworkFile.getParentFile().mkdirs();
//                try {
//                    multipartFile.transferTo(destinationHomeworkFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                newFile.setFileName(destinationHomeworkFileName);
                newFile.setFileOriginName(sourceFileName);
//                newFile.setFilePath(homeworkPath);
                homeworkFileRepository.save(newFile);
            }
        }
        return homework;
    }

    @Override
    @CacheEvict(value = "hwDetail",key = "hwId")
    public void deleteHomework(Integer hwId, String tchrId) {
        homeworkRepositorySupport.deleteHomeworkByHwId(hwId, tchrId);
    }

    //학생 과제 제출률 조회 10분마다
    @Override
    @Cacheable(value = "HwRateBystId",key = "#stId")
    public int[] countBystId(String stId) {
        List<Homework> homeworks = homeworkRepositorySupport.findHomeworkBystId(stId);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadline;
        int[] ret = new int[6]; // 마감전, 마감후, 제출전, 제출후, 채점전, 채점후
        for (Homework homework : homeworks) {
            deadline = homework.getHwDeadline();
            if (now.isBefore(deadline)) {
                ret[0] ++;
                for (StudentHomework studenthomework : homework.getStudentHomeworks()) {
                    if (Objects.equals(studenthomework.getStId(), stId)) {
                        if (studenthomework.getStHwposted() == null) {
                            ret[2] ++;
                        } else {
                            ret[3] ++;
                        }
                        if (studenthomework.getStHwscore() == null) {
                            ret[4] ++;
                        } else {
                            ret[5] ++;
                        }

                    }
                }
            } else {
                ret[1] ++;
                for (StudentHomework studenthomework : homework.getStudentHomeworks()) {
                    if (Objects.equals(studenthomework.getStId(), stId)) {
                        if (studenthomework.getStHwposted() == null) {
                            ret[2] ++;
                        } else {
                            ret[3] ++;
                        }
                        if (studenthomework.getStHwscore() == null) {
                            ret[4] ++;
                        } else {
                            ret[5] ++;
                        }

                    }
                }
            }
        }

        return ret;
    }

    //선생 과제 제출률 확인 조회 10분마다
    @Override
    @Cacheable(value = "HwRateBytchrId",key = "#tchrId")
    public List<int[]> countBytchrId(String tchrId) {
        System.out.println("countBystId..............."+tchrId);
        return homeworkRepository.counthomeworkBytchrId(tchrId);
    }

    @Override
    public List<Homework> findHomeworkBystId(String stId) {
        return homeworkRepositorySupport.findHomeworkBystId(stId);
    }

    //수업에 포함된 과제 조회
    @Override
    public List<Homework> findHomeworkByStudyId(Integer studyId) {
        return homeworkRepositorySupport.findHomeworkByStudyId(studyId);
    }

    @Override
    public List<Homework> findHomeworkByTchrId(String tchrId) {
        return homeworkRepositorySupport.findHomeworkByTchrId(tchrId);
    }

    @Override
    @Cacheable(value = "hwDetail",key = "#hwId")
    public Homework findHomeworkByHwId(Integer hwId) {
        Homework hw = homeworkRepositorySupport.findHomeworkByHwId(hwId).get();
        System.out.println("findHomeworkByHwId..............."+hwId);
        return hw;
    }

    @Override
    @CachePut(value = "hwDetail",key = "#homeworkModifyReq.hwId")
    public Homework updateHomework(HomeworkModifyReq homeworkModifyReq) throws IOException{
        Homework homework = homeworkRepositorySupport.findHomeworkByHwId(homeworkModifyReq.getHwId()).get();
        String[] dl = homeworkModifyReq.getHwDeadline().split("[/ :]");
        LocalDateTime deadline = LocalDateTime.of(
                Integer.parseInt(dl[0]),
                Integer.parseInt(dl[1]),
                Integer.parseInt(dl[2]),
                Integer.parseInt(dl[3]),
                Integer.parseInt(dl[4]),
                Integer.parseInt(dl[5]));
        homework.setHwTitle(homeworkModifyReq.getHwTitle());
        homework.setHwContent(homeworkModifyReq.getHwContent());
        homework.setHwDeadline(deadline);
        homework.setHwPosted(LocalDateTime.now());
        homeworkRepository.save(homework);
        homeworkFileRepository.deleteHomeworkFileByHwId(homeworkModifyReq.getHwId());
        if (!homeworkModifyReq.getHwFile().get(0).isEmpty()) {
            List<MultipartFile> hwFile = homeworkModifyReq.getHwFile();
            for (MultipartFile multipartFile : hwFile) {
                LocalDateTime now = LocalDateTime.now();
                HomeworkFile newFile = new HomeworkFile();
                newFile.setHwId(homework.getHwId());

                String sourceFileName = multipartFile.getOriginalFilename();
//                File destinationHomeworkFile;
                String destinationHomeworkFileName;
//                String homeworkPath = "./assets/homework/teacher_homework/";
//
                String today = now.format(DateTimeFormatter.ofPattern("MMddHHmmssSSS"));
                destinationHomeworkFileName = "HW" + today + sourceFileName;

                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(multipartFile.getSize());
                objectMetadata.setContentType(multipartFile.getContentType());
                amazonS3.putObject(new PutObjectRequest(bucket, destinationHomeworkFileName, multipartFile.getInputStream(), objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
//                destinationHomeworkFile = new File(homeworkPath + destinationHomeworkFileName);
//
//                destinationHomeworkFile.getParentFile().mkdirs();
//                try {
//                    multipartFile.transferTo(destinationHomeworkFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                newFile.setFileName(destinationHomeworkFileName);
                newFile.setFileOriginName(sourceFileName);
//                newFile.setFilePath(homeworkPath);
                homeworkFileRepository.save(newFile);
            }
        }

        return homework;
    }

    @Override
    public URL loadAsResource(String fileName){
        return amazonS3.getUrl(bucket, fileName);
    }
}
