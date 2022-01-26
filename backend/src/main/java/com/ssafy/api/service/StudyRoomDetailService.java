package com.ssafy.api.service;

import com.querydsl.core.Tuple;
import com.ssafy.api.request.StudyRoomAddPostReq;
import com.ssafy.api.request.StudyRoomStudentDeleteReq;
import com.ssafy.db.entity.Student;
import com.ssafy.db.entity.Studyroom;
import com.ssafy.db.entity.StudyroomDetail;

import java.util.List;

public interface StudyRoomDetailService {
    //학생 추가
    void addStudent(StudyRoomAddPostReq studyRoomAddPostReq);
    List<Studyroom> findStudyroombystId(String stId);
    List<Object[]> findStudyroomDetail(int studyId);
//    void deleteStudent(StudyRoomStudentDeleteReq studyRoomStudentDeleteReq);
}
