package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudent is a Querydsl query type for Student
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudent extends EntityPathBase<Student> {

    private static final long serialVersionUID = 1862019129L;

    public static final QStudent student = new QStudent("student");

    public final StringPath stBirthday = createString("stBirthday");

    public final StringPath stContact = createString("stContact");

    public final StringPath stEmail = createString("stEmail");

    public final StringPath stId = createString("stId");

    public final StringPath stName = createString("stName");

    public final StringPath stOriginProfName = createString("stOriginProfName");

    public final StringPath stPassword = createString("stPassword");

    public final StringPath stProfName = createString("stProfName");

    public final StringPath stProfPath = createString("stProfPath");

    public final StringPath stSchool = createString("stSchool");

    public QStudent(String variable) {
        super(Student.class, forVariable(variable));
    }

    public QStudent(Path<? extends Student> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudent(PathMetadata metadata) {
        super(Student.class, metadata);
    }

}

