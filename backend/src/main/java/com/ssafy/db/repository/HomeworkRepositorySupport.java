package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HomeworkRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QHomework qHomework = QHomework.homework;


    public List<String> findHomeworkByStudyId(Integer studyId) {
        List<String> list = jpaQueryFactory.select(qHomework.hwTitle).from(qHomework)
                .where(qHomework.studyId.eq(studyId)).fetch();
        return list;
    }

    public List<String> findHomeworkByTchrId(String tchrId) {
        List<String> list = jpaQueryFactory.select(qHomework.hwTitle).from(qHomework)
                .where(qHomework.tchrId.eq(tchrId)).fetch();
        return list;
    }

    public void deleteHomeworkByHwId(Integer hwId){
        jpaQueryFactory.delete(qHomework).where(qHomework.hwId.eq(hwId)).execute();
    }
}
