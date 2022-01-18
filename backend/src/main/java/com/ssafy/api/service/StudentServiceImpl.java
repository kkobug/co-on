package com.ssafy.api.service;

import com.ssafy.api.request.StudentRegisterPostReq;
import com.ssafy.api.request.StudentUpdatePutReq;
import com.ssafy.db.entity.Student;
import com.ssafy.db.repository.StudentRepository;
import com.ssafy.db.repository.StudentRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentRepositorySupport studentRepositorySupport;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Student createStudent(StudentRegisterPostReq studentRegisterInfo) {
        Student student = new Student();
        student.setStId(studentRegisterInfo.getSt_id());
        student.setStName(studentRegisterInfo.getSt_name());
        student.setStEmail(studentRegisterInfo.getSt_email());
        student.setStPassword(passwordEncoder.encode(studentRegisterInfo.getSt_password()));
        student.setStContact(studentRegisterInfo.getSt_contact());
        student.setStSchool(studentRegisterInfo.getSt_school());
        return studentRepository.save(student);
    }

    @Override
    public Student findById(String stId) {
        Student student = studentRepositorySupport.findById(stId).get();
        return student;
    }

    @Override
    public void deleteStudent(String st_id) {
        studentRepository.deleteById(st_id);
    }

    @Override
    public Student updateStudent(StudentUpdatePutReq studentUpdatePutReq) {
        Student student = new Student();
        student.setStId(studentUpdatePutReq.getSt_id());
        student.setStName(studentUpdatePutReq.getSt_name());
        student.setStEmail(studentUpdatePutReq.getSt_email());
        student.setStPassword(passwordEncoder.encode(studentUpdatePutReq.getSt_password()));
        student.setStContact(studentUpdatePutReq.getSt_contact());
        student.setStSchool(studentUpdatePutReq.getSt_school());
        return studentRepository.save(student);
    }
}
