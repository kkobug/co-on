package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.QHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HomeworkRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QHomework qHomework = QHomework.homework;

    public List<Homework> findHomeworkByStudyId(Integer studyId) {
        List<Homework> list = jpaQueryFactory.select(qHomework).from(qHomework)
                .where(qHomework.studyId.eq(studyId)).fetch();
        return list;
    }

    public List<Homework> findHomeworkByTchrId(String tchrId) {
        List<Homework> list = jpaQueryFactory.select(qHomework).from(qHomework)
                .where(qHomework.tchrId.eq(tchrId)).fetch();
        return list;
    }

    public Optional<Homework> findHomeworkByHwId(Integer hwId) {
        Homework homework = jpaQueryFactory.select(qHomework).from(qHomework)
                .where(qHomework.hwId.eq(hwId)).fetchOne();
        if(homework == null) return Optional.empty();
        return Optional.ofNullable(homework);
    }

    public void deleteHomeworkByHwId(int hwId, String tchrId){
        jpaQueryFactory.delete(qHomework).where(qHomework.hwId.eq(hwId).and(qHomework.tchrId.eq(tchrId))).execute();
    }
}
