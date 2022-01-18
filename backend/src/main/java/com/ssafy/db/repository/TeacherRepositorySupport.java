package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QTeacher qTeacher = QTeacher.teacher;
}
