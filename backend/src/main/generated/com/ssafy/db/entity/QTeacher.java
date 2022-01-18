package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTeacher extends EntityPathBase<Teacher> {

    private static final long serialVersionUID = 846542477L;

    public static final QTeacher teacher = new QTeacher("teacher");

    public final QBaseEntity _super = new QBaseEntity();

    public final StringPath tchr_id = createString("tchr_id");

    public final StringPath tchr_name = createString("tchr_name");

    public final StringPath tchr_email = createString("tchr_email");

    public final StringPath tchr_password = createString("tchr_password");

    public final StringPath tchr_contact = createString("tchr_contact");

    public final StringPath tchr_school = createString("tchr_school");

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

