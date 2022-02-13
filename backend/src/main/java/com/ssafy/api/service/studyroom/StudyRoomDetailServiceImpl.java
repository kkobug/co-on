package com.ssafy.api.service.studyroom;

import com.ssafy.api.request.studyroom.StudyRoomAddPostReq;
import com.ssafy.api.request.studyroomdetail.StudyRoomDetailDeleteReq;
import com.ssafy.api.request.studyroomdetail.StudyRoomDetailPutReq;
import com.ssafy.db.entity.Studyroom;
import com.ssafy.db.entity.StudyroomDetail;
import com.ssafy.db.repository.studyroom.StudyRoomDetailRepository;
import com.ssafy.db.repository.studyroom.StudyRoomdetailRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("studyRoomDetailService")
public class StudyRoomDetailServiceImpl implements StudyRoomDetailService{
    @Autowired
    StudyRoomDetailRepository studyRoomDetailRepository;
    @Autowired
    StudyRoomdetailRepositorySupport studyRoomdetailRepositorySupport;


    //학생 추가   --> 우리반 조회 캐시 변겅, ((학생 출석 개수 조회 변경.10분지나면 조회가능))
    @Override
    @CachePut(value = "findStudent",key = "#studyRoomAddPostReq.studyId")
    @CacheEvict(value = "findStudyroomBystId", key = "#studyRoomAddPostReq.stId")
    public void addStudent(StudyRoomAddPostReq studyRoomAddPostReq) {
        Optional<StudyroomDetail> exist_check = studyRoomdetailRepositorySupport.findStudyroomByIds(studyRoomAddPostReq.getStId(), studyRoomAddPostReq.getStudyId());

        if (!exist_check.isPresent()) {
            StudyroomDetail detail = new StudyroomDetail();
            detail.setStudyId(studyRoomAddPostReq.getStudyId());
            detail.setTchrId(studyRoomAddPostReq.getTchrId());
            detail.setStId(studyRoomAddPostReq.getStId());
            detail.setStPoint(0);   // Column 'st_point' cannot be null
            studyRoomDetailRepository.save(detail);
        }
    }

    @Override
    public List<Studyroom> findStudyroombystId(String stId) {
        return studyRoomdetailRepositorySupport.findStudyroomBystId(stId);
    }

    @Override
    public List<Object[]> findStudyroombystudyId(int studyId) {
        return studyRoomDetailRepository.findStudyroombystudyId(studyId);
    }

    //학생 수업 조회
    @Override
    @Cacheable(value = "findStudyroomBystId", key = "#stId")
    public List<Object[]> findStudyroomAndconbystId(String stId) {
        return studyRoomDetailRepository.findStudyroomAndconbystId(stId);
    }

    @Override
    @CachePut(value = "findStudent",key = "#studyRoomDetailDeleteReq.studyId")
    @CacheEvict(value = "findStudyroomBystId", key = "#studyRoomDetailDeleteReq.stId")
    public void deleteStudent(StudyRoomDetailDeleteReq studyRoomDetailDeleteReq) {
        Integer studyId = studyRoomDetailDeleteReq.getStudyId();
        String stId = studyRoomDetailDeleteReq.getStId();
        System.out.println("deleteStudent실행........................"+studyId);
        studyRoomdetailRepositorySupport.deleteStudyRoomDetail(studyId, stId);
    }

    //우리반 조회, 학생 추가, 학생삭제시cache 변경
    @Override
    @Cacheable(value = "findStudent",key = "#studyId")
    public List<Object[]> findStudentbystudyId(int studyId) {
        System.out.println("findStudentbystudyId실행........................"+studyId);
        return studyRoomDetailRepository.findstudentbystudyId(studyId);
    }

    @Override
    public void updateScore(StudyRoomDetailPutReq studyRoomDetailPutReq) {
        int studyId = studyRoomDetailPutReq.getStudyId();
        String stId = studyRoomDetailPutReq.getStId();
        int score = studyRoomDetailPutReq.getPoint();
        studyRoomDetailRepository.updateScore(score, studyId, stId);
    }

//    @Override
//    public List<Tuple> findstIdAndstName() {
//        return studyRoomdetailRepositorySupport.findstIdAndstName();
//    }
}
