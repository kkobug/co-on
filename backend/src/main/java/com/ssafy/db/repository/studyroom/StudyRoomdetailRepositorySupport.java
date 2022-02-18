package com.ssafy.db.repository.studyroom;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class StudyRoomdetailRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QStudyroom qStudyroom = QStudyroom.studyroom;
    QStudent qStudent = QStudent.student;
    QStudyroomDetail qStudyroomDetail = QStudyroomDetail.studyroomDetail;

    public List<Studyroom> findStudyroomBystId(String stId) {
        return jpaQueryFactory.select(qStudyroom).from(qStudyroom)
                .join(qStudyroomDetail).on(qStudyroomDetail.studyId.eq(qStudyroom.studyId))
                .where(qStudyroomDetail.stId.eq(stId)).fetch();
    }

//    public List<Tuple> findstIdAndstName(){
//        List<Tuple> list = jpaQueryFactory.select(qStudent.stId,qStudent.stName).from(qStudent).fetch();
//        return list;
//    }

    public StudyroomDetail findStudyroomByIds(String stId, Integer studyId) {
        Optional<StudyroomDetail> studyroomDetail = Optional.ofNullable(jpaQueryFactory.select(qStudyroomDetail).from(qStudyroomDetail).where(qStudyroomDetail.studyId.eq(studyId).and(qStudyroomDetail.stId.eq(stId))).fetchOne());
        return studyroomDetail.orElse(null);
    }

    @Transactional
    public void deleteStudyRoomDetail(Integer studyId, String stId){
        jpaQueryFactory.delete(qStudyroomDetail).where(qStudyroomDetail.studyId.eq(studyId).and(qStudyroomDetail.stId.eq(stId))).execute();
    }

    public List<StudyroomDetail> findStudyroomDetailbystId(String stId) {
        return jpaQueryFactory.select(qStudyroomDetail).from(qStudyroomDetail)
                .where(qStudyroomDetail.stId.eq(stId)).fetch();
    }

}
