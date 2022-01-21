package com.ssafy.api.service;

import com.ssafy.api.request.TeacherModifyPutReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.TeacherRegisterPostReq;
import com.ssafy.db.entity.Teacher;
import com.ssafy.db.repository.TeacherRepository;
import com.ssafy.db.repository.TeacherRepositorySupport;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	TeacherRepositorySupport teacherRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;

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
	public Teacher modifyTeacher(TeacherModifyPutReq teacherModifyInfo) {
		Teacher teacher = new Teacher();
		teacher.setTchrId(teacherModifyInfo.getTchr_id());
		teacher.setTchrName(teacherModifyInfo.getTchr_name());
		teacher.setTchrEmail(teacherModifyInfo.getTchr_email());
		teacher.setTchrContact(teacherModifyInfo.getTchr_contact());
		teacher.setTchrSchool(teacherModifyInfo.getTchr_school());
		teacher.setTchrBirthday(teacherModifyInfo.getTchr_birthday());
		return teacherRepository.save(teacher);
	}

	@Override
	public Teacher findById(String tchrId) {
		Teacher teacher = teacherRepositorySupport.findById(tchrId).get();
		return teacher;
	}

	@Override
	public void deleteTeacher(String tchr_id) {
		teacherRepository.deleteById(tchr_id);
	}

	@Override
	public Teacher changeTeacherPassword(Teacher teacher) {
		teacher.setTchrPassword(passwordEncoder.encode(teacher.getTchrPassword()));
		return teacherRepository.save(teacher);
	}

	@Override
	public String findByName(String tchrName) {
		String tchrId = teacherRepositorySupport.findIdByName(tchrName);
		return tchrId;
	}
}
