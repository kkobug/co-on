package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudentHomework is a Querydsl query type for StudentHomework
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudentHomework extends EntityPathBase<StudentHomework> {

    private static final long serialVersionUID = 1317042121L;

    public static final QStudentHomework studentHomework = new QStudentHomework("studentHomework");

    public final NumberPath<Integer> hwId = createNumber("hwId", Integer.class);

    public final StringPath stHwcontent = createString("stHwcontent");

    public final ListPath<StudentHomeworkFile, QStudentHomeworkFile> stHwFile = this.<StudentHomeworkFile, QStudentHomeworkFile>createList("stHwFile", StudentHomeworkFile.class, QStudentHomeworkFile.class, PathInits.DIRECT2);

    public final NumberPath<Integer> stHwId = createNumber("stHwId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> stHwposted = createDateTime("stHwposted", java.time.LocalDateTime.class);

    public final NumberPath<Integer> stHwscore = createNumber("stHwscore", Integer.class);

    public final StringPath stId = createString("stId");

    public final NumberPath<Integer> studyId = createNumber("studyId", Integer.class);

    public final StringPath tchrId = createString("tchrId");

    public QStudentHomework(String variable) {
        super(StudentHomework.class, forVariable(variable));
    }

    public QStudentHomework(Path<? extends StudentHomework> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudentHomework(PathMetadata metadata) {
        super(StudentHomework.class, metadata);
    }

}

