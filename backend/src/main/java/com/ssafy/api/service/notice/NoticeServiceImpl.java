package com.ssafy.api.service.notice;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.ssafy.api.request.notice.NoticeRegisterPostReq;
import com.ssafy.api.request.notice.NoticeUpdatePutReq;
import com.ssafy.db.entity.Notice;
import com.ssafy.db.entity.NoticeFile;
import com.ssafy.db.repository.notice.NoticeFileRepository;
import com.ssafy.db.repository.notice.NoticeRepository;
import com.ssafy.db.repository.notice.NoticeRepositorySupport;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    NoticeFileRepository noticeFileRepository;

    @Autowired
    NoticeRepositorySupport noticeRepositorySupport;

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override // 공지사항 등록
    public Notice createNotice(NoticeRegisterPostReq noticeRegisterPostReq) throws IOException{
        System.out.println("run service!!!!!!!!!");
        Notice notice = new Notice();
        notice.setStudyId(noticeRegisterPostReq.getStudyId());
        notice.setTchrId(noticeRegisterPostReq.getTchrId());
        notice.setNoticeTitle(noticeRegisterPostReq.getNoticeTitle());
        notice.setNoticeContent(noticeRegisterPostReq.getNoticeContent());
//        System.out.println(noticeRegisterPostReq.getNoticeFile());
        noticeRepository.save(notice);
        System.out.println(notice.getNoticeId());
        if (!noticeRegisterPostReq.getNoticeFile().get(0).isEmpty()) {
            List<MultipartFile> noticeFile = noticeRegisterPostReq.getNoticeFile();
            for (MultipartFile multipartFile : noticeFile) {
                NoticeFile newFile = new NoticeFile();
                newFile.setNoticeId(notice.getNoticeId());

                String sourceFileName = multipartFile.getOriginalFilename();
                String destinationNoticeFileName;

                LocalDateTime now = LocalDateTime.now();
                String today = now.format(DateTimeFormatter.ofPattern("MMddHHmmss"));
                destinationNoticeFileName = "NT" + today + sourceFileName;

                String noticePath = "temp";
//                File destinationNoticeFile;
//                destinationNoticeFile = new File(noticePath + destinationNoticeFileName);
//                destinationNoticeFile.getParentFile().mkdirs();
//                multipartFile.transferTo(destinationNoticeFile);
//                try {
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(multipartFile.getSize());
                objectMetadata.setContentType(multipartFile.getContentType());
                amazonS3.putObject(new PutObjectRequest(bucket, destinationNoticeFileName, multipartFile.getInputStream(), objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
//                try (InputStream inputStream = multipartFile.getInputStream()) {
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                newFile.setFileName(destinationNoticeFileName);
                newFile.setFileOriginName(sourceFileName);
                newFile.setFilePath(noticePath);

                noticeFileRepository.save(newFile);
            }
        }
        return notice;
    }

    @Override // 공지사항 삭제
    @CacheEvict(value = "findNotice",key ="#noticeId")
    public void deleteNotice(Integer noticeId, String tchrId) {
        noticeRepositorySupport.deleteNoticeByNoticeIdAndTchrId(noticeId, tchrId);
    }

    @Override // 공지사항 상세 조회
    @Cacheable(value = "findNotice",key = "#NoticeId")
    public Notice findBynoticeId(Integer NoticeId) {
        Notice notice = noticeRepositorySupport.findByNoticeId(NoticeId).get();
        System.out.println("Call findBynoticeId..............................."+NoticeId);
        return notice;
    }

    @Override
    @CachePut(value = "findNotcie",key = "#noticeUpdatePutReq.noticeId")
    public Notice updateNotice(NoticeUpdatePutReq noticeUpdatePutReq) throws IOException{
        Notice notice = noticeRepositorySupport.findByNoticeId(noticeUpdatePutReq.getNoticeId()).get();
        notice.setNoticeTitle(noticeUpdatePutReq.getNoticeTitle());
        notice.setNoticeContent(noticeUpdatePutReq.getNoticeContent());
        notice.setNoticePosted(LocalDateTime.now());
        noticeRepository.save(notice);
        noticeFileRepository.deleteNoticeFileByNoticeId(noticeUpdatePutReq.getNoticeId());
        if (!noticeUpdatePutReq.getNoticeFile().get(0).isEmpty()) {
            List<MultipartFile> noticeFile = noticeUpdatePutReq.getNoticeFile();
            for (MultipartFile multipartFile : noticeFile) {
                NoticeFile newFile = new NoticeFile();
                newFile.setNoticeId(notice.getNoticeId());

                String sourceFileName = multipartFile.getOriginalFilename();
                String destinationNoticeFileName;

                LocalDateTime now = LocalDateTime.now();
                String today = now.format(DateTimeFormatter.ofPattern("MMddHHmmss"));
                destinationNoticeFileName = "NT" + today + sourceFileName;
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(multipartFile.getSize());
                objectMetadata.setContentType(multipartFile.getContentType());
                amazonS3.putObject(new PutObjectRequest(bucket, destinationNoticeFileName, multipartFile.getInputStream(), objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

                newFile.setFileName(destinationNoticeFileName);
                newFile.setFileOriginName(sourceFileName);
                noticeFileRepository.save(newFile);
            }
        }
        return notice;
    }

    @Override
    public List<Notice> findNoticeByStudyId(Integer studyId) {
        return noticeRepositorySupport.findNoticeByStudyId(studyId);
    }

    @Override
    public List<Notice> findNoticeByTchrId(String tchrId) {
        return noticeRepositorySupport.findNoticeByTchrId(tchrId);
    }

    @Override
    public List<Notice> findNoticeBystId(String stId) {
        return noticeRepositorySupport.findNoticeBystId(stId);
    }

    @Override
    public ResponseEntity<byte[]> loadAsResource(String fileName) throws IOException{
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucket, fileName));
        S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(s3ObjectInputStream);

        String newFileName = URLEncoder.encode(fileName, "UTF-8");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", newFileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }
}
