package com.ssafy.api.service;

import com.querydsl.core.Tuple;
import com.ssafy.api.request.StudyRoomAddPostReq;
import com.ssafy.api.request.StudyRoomStudentDeleteReq;
import com.ssafy.db.entity.Student;
import com.ssafy.db.entity.Studyroom;
import com.ssafy.db.entity.StudyroomDetail;
import com.ssafy.db.repository.StudyRoomDetailRepository;
import com.ssafy.db.repository.StudyRoomdetailRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studyRoomDetailService")
public class StudyRoomDetailServiceImpl implements StudyRoomDetailService{
    @Autowired
    StudyRoomDetailRepository studyRoomDetailRepository;
    @Autowired
    StudyRoomdetailRepositorySupport studyRoomdetailRepositorySupport;


    @Override
    public void addStudent(StudyRoomAddPostReq studyRoomAddPostReq) {
        StudyroomDetail detail = new StudyroomDetail();
        detail.setStudyId(studyRoomAddPostReq.getStudyId());
        detail.setTchrId(studyRoomAddPostReq.getTchrId());
        detail.setStId(studyRoomAddPostReq.getStId());
        studyRoomDetailRepository.save(detail);
    }

    @Override
    public List<Studyroom> findStudyroombystId(String stId) {
        return studyRoomdetailRepositorySupport.findStudyroomBystId(stId);
    }

    @Override
    public List<Object[]> findStudyroomDetail(int studyId) {
        return studyRoomDetailRepository.findstIdAndstNameBystudyId(studyId);
    }

//    @Override
//    public void deleteStudent(StudyRoomStudentDeleteReq studyRoomStudentDeleteReq) {
//        int studyId = studyRoomStudentDeleteReq.getStudyId();
//        String stId = studyRoomStudentDeleteReq.getStId();
//        studyRoomDetailRepository.deleteBystIdAndstudyId(studyId, stId);
//    }

}
