package com.ssafy.api.service;

import com.ssafy.api.request.StudyRoomAddPostReq;
import com.ssafy.api.request.StudyRoomRegisterPostReq;
import com.ssafy.db.entity.Studyroom;
import com.ssafy.db.repository.StudyRoomDetailRepository;
import com.ssafy.db.repository.StudyRoomRepository;
import com.ssafy.db.repository.StudyRoomRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("studyRoomService")
public class StudyRoomServiceImpl implements StudyRoomService {
    @Autowired
    StudyRoomRepository studyRoomRepository;
    @Autowired
    StudyRoomRepositorySupport studyRoomRepositorySupport;
    @Autowired
    StudyRoomDetailRepository studyRoomDetailRepository;
    @Override
    public Studyroom createStudyRoom(StudyRoomRegisterPostReq studyRoomRegisterPostReq) {
        Studyroom studyRoom = new Studyroom();
        studyRoom.setTchrId(studyRoomRegisterPostReq.getTchrId());
        studyRoom.setStudyName(studyRoomRegisterPostReq.getStudyName());
        studyRoom.setStudyDesc(studyRoomRegisterPostReq.getStudyDesc());
        return studyRoomRepository.save(studyRoom);
    }

    @Override
    public void deleteStudyRoom(String tchrId, String studyName) {
        studyRoomRepositorySupport.deleteStudyRoomByTchrIdAndStudyName(tchrId, studyName);
    }

    @Override
    public List<String> findstudyNamebytchrId(String tchrId) {
        return studyRoomRepositorySupport.findStudyNameByTchrId(tchrId);
    }
}
