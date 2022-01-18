package com.ssafy.db.repository;

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

    public  Optional<Student> findById(String stId) {
        Student student = jpaQueryFactory.select(qStudent).from(qStudent)
                .where(qStudent.stId.eq(stId)).fetchOne();
        if(student == null) return Optional.empty();
        return Optional.ofNullable(student);
    }
}
