package com.ssafy.api.service;

import com.querydsl.core.Tuple;
import com.ssafy.api.request.StudyRoomAddPostReq;
import com.ssafy.db.entity.Student;
import com.ssafy.db.entity.Studyroom;

import java.util.List;

public interface StudyRoomDetailService {
    //학생 추가
    void addStudent(StudyRoomAddPostReq studyRoomAddPostReq);
    List<Studyroom> findStudyroombystId(String stId);
//    List<Tuple> findstIdAndstName();
}
