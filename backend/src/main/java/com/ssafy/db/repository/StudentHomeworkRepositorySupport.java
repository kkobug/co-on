package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QNotice;
import com.ssafy.db.entity.QStudentHomework;
import com.ssafy.db.entity.StudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class StudentHomeworkRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QStudentHomework qStudentHomework = QStudentHomework.studentHomework;

    public void deleteStudentHomeworkByStHwIdAndStId(int stHwId, String stId) {
        jpaQueryFactory.delete(qStudentHomework).where(qStudentHomework.stHwId.eq(stHwId).and(qStudentHomework.stId.eq(stId))).execute();  //excute 추가
    }

    public List<StudentHomework> findStudentHomeworkByHwId(int hwId){
        List<StudentHomework> list = jpaQueryFactory.select(qStudentHomework).from(qStudentHomework)
                .where(qStudentHomework.hwId.eq(hwId)).fetch();
        return list;
    }

    public List<StudentHomework> findStudentHomeworkByStId(String stId) {
        List<StudentHomework> list = jpaQueryFactory.select(qStudentHomework).from(qStudentHomework)
                .where(qStudentHomework.stId.eq(stId)).fetch();
        return list;
    }

    public Optional<StudentHomework> findBystHwId(int stHwId){
        StudentHomework studentHomework = jpaQueryFactory.select(qStudentHomework).from(qStudentHomework)
                .where(qStudentHomework.stHwId.eq(stHwId)).fetchOne();
        if(studentHomework == null) return Optional.empty();
        return Optional.ofNullable(studentHomework);
    }
}
