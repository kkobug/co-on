package com.ssafy.api.service.studyroom;

import com.ssafy.api.request.studyroom.StudyRoomAddPostReq;
import com.ssafy.api.request.studyroomdetail.StudyRoomDetailDeleteReq;
import com.ssafy.api.request.studyroomdetail.StudyRoomDetailPutReq;
import com.ssafy.db.entity.Studyroom;
import com.ssafy.db.entity.StudyroomDetail;
import com.ssafy.db.repository.studyroom.StudyRoomDetailRepository;
import com.ssafy.db.repository.studyroom.StudyRoomdetailRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("studyRoomDetailService")
public class StudyRoomDetailServiceImpl implements StudyRoomDetailService{
    @Autowired
    StudyRoomDetailRepository studyRoomDetailRepository;
    @Autowired
    StudyRoomdetailRepositorySupport studyRoomdetailRepositorySupport;


    @Override
    public void addStudent(StudyRoomAddPostReq studyRoomAddPostReq) {
        System.out.println("!!!!!!!!!!!!serve");
        StudyroomDetail exist_check = studyRoomdetailRepositorySupport.findStudyroomByIds(studyRoomAddPostReq.getStId(), studyRoomAddPostReq.getStudyId());
        System.out.println("!!!!!!!!!!!!exist cherk");
        System.out.println(exist_check);

        if (exist_check == null) {
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

    @Override
    public List<Object[]> findStudyroomAndconbystId(String stId) {
        return studyRoomDetailRepository.findStudyroomAndconbystId(stId);
    }

    public void deleteStudent(StudyRoomDetailDeleteReq studyRoomDetailDeleteReq) {
        Integer studyId = studyRoomDetailDeleteReq.getStudyId();
        String stId = studyRoomDetailDeleteReq.getStId();
        studyRoomdetailRepositorySupport.deleteStudyRoomDetail(studyId, stId);
    }

    @Override
    public List<Object[]> findStudentbystudyId(int studyId) {
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
