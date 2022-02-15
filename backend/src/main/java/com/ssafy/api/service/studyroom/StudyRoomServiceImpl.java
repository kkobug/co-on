package com.ssafy.api.service.studyroom;

import com.ssafy.api.request.studyroom.StudyRoomRegisterPostReq;
import com.ssafy.db.entity.Studyroom;
import com.ssafy.db.repository.studyroom.StudyRoomDetailRepository;
import com.ssafy.db.repository.studyroom.StudyRoomRepository;
import com.ssafy.db.repository.studyroom.StudyRoomRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("studyRoomService")
public class StudyRoomServiceImpl implements StudyRoomService {
    @Autowired
    StudyRoomRepository studyRoomRepository;
    @Autowired
    StudyRoomRepositorySupport studyRoomRepositorySupport;

    @Override
    @CacheEvict(value = "TchrClassList", key = "#studyRoomRegisterPostReq.tchrId")
    public Studyroom createStudyRoom(StudyRoomRegisterPostReq studyRoomRegisterPostReq) {
        Studyroom studyRoom = new Studyroom();
        studyRoom.setTchrId(studyRoomRegisterPostReq.getTchrId());
        studyRoom.setStudyName(studyRoomRegisterPostReq.getStudyName());
        studyRoom.setStudyDesc(studyRoomRegisterPostReq.getStudyDesc());
        return studyRoomRepository.save(studyRoom);
    }

    @Override
    @CacheEvict(value = "TchrClassList", key = "#tchrId")
    public void deleteStudyRoom(String tchrId, String studyName) {
        studyRoomRepositorySupport.deleteStudyRoomByTchrIdAndStudyName(tchrId, studyName);
    }

    //교사 수업 조회  --> 교사 수업 추가, 삭제 시 캐시 변경
    @Override
    @Cacheable(value = "TchrClassList",key = "#tchrId")
    public List<Object[]> findstudyNamebytchrId(String tchrId) {
        List<Object[]> list = studyRoomRepository.selectstudyIdAndstudyNamebytchrId(tchrId);
        System.out.println("findstudyNamebytchrId................"+tchrId);
        return list;
    }

}
