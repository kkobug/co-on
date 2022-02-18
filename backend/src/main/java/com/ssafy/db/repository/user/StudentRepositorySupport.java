package com.ssafy.db.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QStudent;
import com.ssafy.db.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StudentRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QStudent qStudent = QStudent.student;

    public  Student findById(String stId) {
        return jpaQueryFactory.select(qStudent).from(qStudent)
                .where(qStudent.stId.eq(stId)).fetchOne();
    }

    public String findstIdBystName(String stName) {
        String stId = jpaQueryFactory.select(qStudent.stId).from(qStudent)
                .where(qStudent.stName.eq(stName)).fetchOne();
        return stId;
    }
}
