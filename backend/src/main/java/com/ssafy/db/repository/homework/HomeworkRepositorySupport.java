package com.ssafy.db.repository.homework;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.QHomework;
import com.ssafy.db.entity.QStudyroomDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HomeworkRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QHomework qHomework = QHomework.homework;
    QStudyroomDetail qStudyroomDetail = QStudyroomDetail.studyroomDetail;

    public List<Homework> findHomeworkByStudyId(Integer studyId) {
        return jpaQueryFactory.select(qHomework).from(qHomework)
                .where(qHomework.studyId.eq(studyId))
                .orderBy(qHomework.hwDeadline.desc())
                .fetch();
    }

    public List<Homework> findHomeworkByTchrId(String tchrId) {
        return jpaQueryFactory.select(qHomework).from(qHomework)
                .where(qHomework.tchrId.eq(tchrId)).
                orderBy(qHomework.hwDeadline.desc())
                .fetch();
    }

    public Optional<Homework> findHomeworkByHwId(Integer hwId) {
        Homework homework = jpaQueryFactory.select(qHomework).from(qHomework)
                .where(qHomework.hwId.eq(hwId)).fetchOne();
        return Optional.ofNullable(homework);
    }

    public void deleteHomeworkByHwId(Integer hwId, String tchrId){
        jpaQueryFactory.delete(qHomework).where(qHomework.hwId.eq(hwId).and(qHomework.tchrId.eq(tchrId))).execute();
    }

    public List<Homework> findHomeworkBystId(String stId){
        return jpaQueryFactory.selectFrom(qHomework).orderBy(qHomework.hwDeadline.desc()).where(qHomework.studyId.in(
                JPAExpressions.select(qStudyroomDetail.studyId).from(qStudyroomDetail).where(qStudyroomDetail.stId.eq(stId))
        )).fetch();
    }
}
