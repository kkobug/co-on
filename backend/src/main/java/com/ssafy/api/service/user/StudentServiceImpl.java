package com.ssafy.api.service.user;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.api.request.user.StudentProfilePutReq;
import com.ssafy.api.request.user.StudentRegisterPostReq;
import com.ssafy.api.request.user.StudentUpdatePutReq;
import com.ssafy.db.entity.Student;
import com.ssafy.db.entity.Teacher;
import com.ssafy.db.repository.user.StudentRepository;
import com.ssafy.db.repository.user.StudentRepositorySupport;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentRepositorySupport studentRepositorySupport;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public Student createStudent(StudentRegisterPostReq studentRegisterInfo) {
        Student student = new Student();
        student.setStId(studentRegisterInfo.getSt_id());
        student.setStName(studentRegisterInfo.getSt_name());
        student.setStEmail(studentRegisterInfo.getSt_email());
        student.setStPassword(passwordEncoder.encode(studentRegisterInfo.getSt_password()));
        student.setStContact(studentRegisterInfo.getSt_contact());
        student.setStSchool(studentRegisterInfo.getSt_school());
        student.setStBirthday(studentRegisterInfo.getSt_birthday());
        return studentRepository.save(student);
    }

    @Override
    @Cacheable(value = "findById", key="#stId")
    public Student findById(String stId) {
        Student student = studentRepositorySupport.findById(stId);
        System.out.println("findByIdStudent...................................................실행"+stId);
        return student;
    }

    @Override
    @CacheEvict(value = "findById", key="#st_id")
    public void deleteStudent(String st_id) {
        studentRepository.deleteById(st_id);
    }

    @Override
    @CachePut(value = "findById",key="#studentUpdatePutReq.st_id")
    public Student updateStudent(StudentUpdatePutReq studentUpdatePutReq) {
        Student student = new Student();
        student.setStId(studentUpdatePutReq.getSt_id());
        student.setStName(studentUpdatePutReq.getSt_name());
        student.setStEmail(studentUpdatePutReq.getSt_email());
        student.setStPassword(passwordEncoder.encode(studentUpdatePutReq.getSt_password()));
        student.setStContact(studentUpdatePutReq.getSt_contact());
        student.setStSchool(studentUpdatePutReq.getSt_school());
        student.setStBirthday(studentUpdatePutReq.getSt_birthday());
        return studentRepository.save(student);
    }

    @Override
    public String findstIdBystName(String stName) {
        String StId = studentRepositorySupport.findstIdBystName(stName);
        return StId;
    }

    @Override
    @CacheEvict(value = "findById",key="#student.stId")
    public Student changeStudentPassword(Student student) {
        student.setStPassword(passwordEncoder.encode(student.getStPassword()));
        return studentRepository.save(student);
    }

    @Override
    @CacheEvict(value = "findById",key="#studentProfilePutReq.stId")
    public Student changeStudentProfile(StudentProfilePutReq studentProfilePutReq) throws IOException{
        Student student = studentRepositorySupport.findById(studentProfilePutReq.getStId());

        if (studentProfilePutReq.getStProfFile().get(0).isEmpty()) {
            System.out.println("!!!!!!!!!!!!!!");
            student.setStProfName(null);
            student.setStProfPath(null);
            student.setStOriginProfName(null);
            studentRepository.save(student);
            return student;
        } else {
            String sourceFileName = studentProfilePutReq.getStProfFile().get(0).getOriginalFilename();
            String destinationProfileName;

            String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmssSSS"));
            destinationProfileName = "Sprof" + today + sourceFileName;

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(studentProfilePutReq.getStProfFile().get(0).getSize());
            objectMetadata.setContentType(studentProfilePutReq.getStProfFile().get(0).getContentType());
            amazonS3.putObject(new PutObjectRequest(
                    bucket,
                    destinationProfileName,
                    studentProfilePutReq.getStProfFile().get(0).getInputStream(),
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead));
//            destinationProfile.getParentFile().mkdirs();
//            try {
//                studentProfilePutReq.getStProfFile().get(0).transferTo(destinationProfile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            student.setStProfName(destinationProfileName);
            student.setStOriginProfName(sourceFileName);
            studentRepository.save(student);
            return student;
        }
    }

    @Override
    public URL loadimg(String fileName){
        return amazonS3.getUrl(bucket, fileName);
    }
}
