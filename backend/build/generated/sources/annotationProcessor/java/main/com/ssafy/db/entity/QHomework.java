package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHomework is a Querydsl query type for Homework
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHomework extends EntityPathBase<Homework> {

    private static final long serialVersionUID = -473149902L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHomework homework = new QHomework("homework");

    public final StringPath hwContent = createString("hwContent");

    public final DateTimePath<java.time.LocalDateTime> hwDeadline = createDateTime("hwDeadline", java.time.LocalDateTime.class);

    public final ListPath<HomeworkFile, QHomeworkFile> hwFile = this.<HomeworkFile, QHomeworkFile>createList("hwFile", HomeworkFile.class, QHomeworkFile.class, PathInits.DIRECT2);

    public final NumberPath<Integer> hwId = createNumber("hwId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> hwPosted = createDateTime("hwPosted", java.time.LocalDateTime.class);

    public final StringPath hwTitle = createString("hwTitle");

    public final ListPath<StudentHomework, QStudentHomework> studentHomeworks = this.<StudentHomework, QStudentHomework>createList("studentHomeworks", StudentHomework.class, QStudentHomework.class, PathInits.DIRECT2);

    public final NumberPath<Integer> studyId = createNumber("studyId", Integer.class);

    public final QStudyroom studyroom;

    public final StringPath tchrId = createString("tchrId");

    public QHomework(String variable) {
        this(Homework.class, forVariable(variable), INITS);
    }

    public QHomework(Path<? extends Homework> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHomework(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHomework(PathMetadata metadata, PathInits inits) {
        this(Homework.class, metadata, inits);
    }

    public QHomework(Class<? extends Homework> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyroom = inits.isInitialized("studyroom") ? new QStudyroom(forProperty("studyroom")) : null;
    }

}

