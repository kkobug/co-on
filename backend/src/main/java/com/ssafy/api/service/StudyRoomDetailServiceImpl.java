package com.ssafy.api.service;

import com.querydsl.core.Tuple;
import com.ssafy.api.request.StudyRoomAddPostReq;
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

//    @Override
//    public List<Tuple> findstIdAndstName() {
//        return studyRoomdetailRepositorySupport.findstIdAndstName();
//    }
}
