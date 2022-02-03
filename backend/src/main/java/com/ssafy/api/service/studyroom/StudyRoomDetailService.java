package com.ssafy.api.service.studyroom;

import com.ssafy.api.request.studyroom.StudyRoomAddPostReq;
import com.ssafy.db.entity.Studyroom;

import java.util.List;

public interface StudyRoomDetailService {
    //학생 추가
    void addStudent(StudyRoomAddPostReq studyRoomAddPostReq);
    List<Studyroom> findStudyroombystId(String stId);
    List<Object[]> findStudyroombystudyId(int studyId);
//    List<Tuple> findstIdAndstName();
}
