package com.ssafy.api.service; //비즈니스 로직 처리를 위한 서비스

import com.ssafy.api.request.StudentRegisterPostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.Student;
import com.ssafy.db.entity.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface StudentService {
	Student createStudent(StudentRegisterPostReq studentRegisterInfo);
	Student findById(String stId);
//	void deleteStudent(String st_id);
}
