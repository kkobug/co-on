package com.ssafy.db.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QStudent;
import com.ssafy.db.entity.QStudyroom;
import com.ssafy.db.entity.QStudyroomDetail;
import com.ssafy.db.entity.Studyroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudyRoomdetailRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QStudyroom qStudyroom = QStudyroom.studyroom;
    QStudent qStudent = QStudent.student;
    QStudyroomDetail qStudyroomDetail = QStudyroomDetail.studyroomDetail;

    public List<Studyroom> findStudyroomBystId(String stId) {
        List<Studyroom> list = jpaQueryFactory.select(qStudyroom).from(qStudyroom)
                .join(qStudyroomDetail).on(qStudyroomDetail.studyId.eq(qStudyroom.studyId))
                .where(qStudyroomDetail.stId.eq(stId)).fetch();
        return list;
    }

}
