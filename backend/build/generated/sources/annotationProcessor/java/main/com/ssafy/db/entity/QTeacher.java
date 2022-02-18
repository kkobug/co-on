package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeacher is a Querydsl query type for Teacher
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTeacher extends EntityPathBase<Teacher> {

    private static final long serialVersionUID = -1993379360L;

    public static final QTeacher teacher = new QTeacher("teacher");

    public final StringPath tchrBirthday = createString("tchrBirthday");

    public final StringPath tchrContact = createString("tchrContact");

    public final StringPath tchrEmail = createString("tchrEmail");

    public final StringPath tchrId = createString("tchrId");

    public final StringPath tchrName = createString("tchrName");

    public final StringPath tchrOriginProfName = createString("tchrOriginProfName");

    public final StringPath tchrPassword = createString("tchrPassword");

    public final StringPath tchrProfName = createString("tchrProfName");

    public final StringPath tchrProfPath = createString("tchrProfPath");

    public final StringPath tchrSchool = createString("tchrSchool");

    public QTeacher(String variable) {
        super(Teacher.class, forVariable(variable));
    }

    public QTeacher(Path<? extends Teacher> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeacher(PathMetadata metadata) {
        super(Teacher.class, metadata);
    }

}

