package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QStudyroom;

import com.ssafy.db.entity.QStudyroomDetail;
import com.ssafy.db.entity.Studyroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class StudyRoomRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QStudyroom qStudyRoom = QStudyroom.studyroom;
    public List<String> findStudyNameByTchrId(String tchrId) {
        List<String> list = jpaQueryFactory.select(qStudyRoom.studyName).from(qStudyRoom)
                .where(qStudyRoom.teacher.tchrId.eq(tchrId)).fetch();
        return list;
    }

    public void deleteStudyRoomByTchrIdAndStudyName(String tchrId, String studyName){
        jpaQueryFactory.delete(qStudyRoom).where(qStudyRoom.tchrId.eq(tchrId).and(qStudyRoom.studyName.eq(studyName))).execute();
    }
}
