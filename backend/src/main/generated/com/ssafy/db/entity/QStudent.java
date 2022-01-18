package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudent extends EntityPathBase<Student> {

    private static final long serialVersionUID = 846542477L;

    public static final QStudent student = new QStudent("student");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath st_id = createString("st_id");

    public final StringPath st_name = createString("st_name");

    public final StringPath st_email = createString("st_email");

    public final StringPath st_password = createString("st_password");

    public final StringPath st_contact = createString("st_contact");

    public final StringPath st_school = createString("st_school");

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

