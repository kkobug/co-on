package com.ssafy.api.service.user;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.api.request.user.TeacherModifyPutReq;
import com.ssafy.api.request.user.TeacherProfilePutReq;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.user.TeacherRegisterPostReq;
import com.ssafy.db.entity.Teacher;
import com.ssafy.db.repository.user.TeacherRepository;
import com.ssafy.db.repository.user.TeacherRepositorySupport;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	TeacherRepository teacherRepository;

	@Autowired
	TeacherRepositorySupport teacherRepositorySupport;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Override
	public Teacher createTeacher(TeacherRegisterPostReq teacherRegisterInfo) {
		Teacher teacher = new Teacher();
		teacher.setTchrId(teacherRegisterInfo.getTchr_id());
		teacher.setTchrName(teacherRegisterInfo.getTchr_name());
		teacher.setTchrEmail(teacherRegisterInfo.getTchr_email());
		teacher.setTchrPassword(passwordEncoder.encode(teacherRegisterInfo.getTchr_password()));
		teacher.setTchrContact(teacherRegisterInfo.getTchr_contact());
		teacher.setTchrSchool(teacherRegisterInfo.getTchr_school());
		teacher.setTchrBirthday(teacherRegisterInfo.getTchr_birthday());
		return teacherRepository.save(teacher);
	}

	@Override
	@CachePut(value = "findById", key = "#teacherModifyInfo.tchr_id")
	public Teacher modifyTeacher(TeacherModifyPutReq teacherModifyInfo) {
		Teacher teacher = new Teacher();
		teacher.setTchrId(teacherModifyInfo.getTchr_id());
		teacher.setTchrName(teacherModifyInfo.getTchr_name());
		teacher.setTchrEmail(teacherModifyInfo.getTchr_email());
		teacher.setTchrPassword(passwordEncoder.encode(teacherModifyInfo.getTchr_password()));
		teacher.setTchrContact(teacherModifyInfo.getTchr_contact());
		teacher.setTchrSchool(teacherModifyInfo.getTchr_school());
		teacher.setTchrBirthday(teacherModifyInfo.getTchr_birthday());
		return teacherRepository.save(teacher);
	}

	@Override
	@Cacheable(value = "findById", key = "#tchrId")
	public Teacher findById(String tchrId) {
		Teacher teacher = teacherRepositorySupport.findById(tchrId).get();
		System.out.println("findByIdTeacher...................................................실행"+tchrId);
		return teacher;
	}

	@Override
	@CacheEvict(value = "findById",key = "#tchr_id")
	public void deleteTeacher(String tchr_id) {
		teacherRepository.deleteById(tchr_id);
	}

	@Override
	@CacheEvict(value = "findById", key = "#teacher.tchrId")
	public Teacher changeTeacherPassword(Teacher teacher) {
		teacher.setTchrPassword(passwordEncoder.encode(teacher.getTchrPassword()));
		return teacherRepository.save(teacher);
	}

	@Override
	public String findByName(String tchrName) {
		String tchrId = teacherRepositorySupport.findIdByName(tchrName);
		return tchrId;
	}

	@Override
	public List<Object[]> findstIdAndstName() {
		List<Object[]> list = teacherRepository.selectAllStudent();
		return list;
	}

	@Override
	@CacheEvict(value = "findById",key = "#teacherProfilePutReq.tchrId")
	public Teacher changeTeacherProfile(TeacherProfilePutReq teacherProfilePutReq) throws IOException{
		Teacher teacher = teacherRepositorySupport.findById(teacherProfilePutReq.getTchrId()).get();

		if (teacherProfilePutReq.getTchrProfFile().get(0).isEmpty()) {
			teacher.setTchrProfName(null);
			teacher.setTchrProfPath(null);
			teacher.setTchrOriginProfName(null);
			teacherRepository.save(teacher);
			return teacher;
		} else {
			String sourceFileName = teacherProfilePutReq.getTchrProfFile().get(0).getOriginalFilename();
			String destinationProfileName;

			String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmssSSS"));
			destinationProfileName = "Tprof" + today + sourceFileName;

			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(teacherProfilePutReq.getTchrProfFile().get(0).getSize());
			objectMetadata.setContentType(teacherProfilePutReq.getTchrProfFile().get(0).getContentType());
			amazonS3.putObject(new PutObjectRequest(
					bucket,
					destinationProfileName,
					teacherProfilePutReq.getTchrProfFile().get(0).getInputStream(),
					objectMetadata
			).withCannedAcl(CannedAccessControlList.PublicRead));
//			destinationProfile.getParentFile().mkdirs();
//			try {
//				teacherProfilePutReq.getTchrProfFile().get(0).transferTo(destinationProfile);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

			teacher.setTchrProfName(destinationProfileName);
			teacher.setTchrOriginProfName(sourceFileName);
			teacherRepository.save(teacher);
			return teacher;
		}
	}

	@Override
	public URL loadimg(String fileName){
		return amazonS3.getUrl(bucket, fileName);
	}
}

