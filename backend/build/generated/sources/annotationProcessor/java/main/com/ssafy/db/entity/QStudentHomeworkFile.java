package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudentHomeworkFile is a Querydsl query type for StudentHomeworkFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudentHomeworkFile extends EntityPathBase<StudentHomeworkFile> {

    private static final long serialVersionUID = -1499540251L;

    public static final QStudentHomeworkFile studentHomeworkFile = new QStudentHomeworkFile("studentHomeworkFile");

    public final StringPath fileExt = createString("fileExt");

    public final NumberPath<Integer> fileId = createNumber("fileId", Integer.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath fileOriginName = createString("fileOriginName");

    public final StringPath filePath = createString("filePath");

    public final StringPath fileSize = createString("fileSize");

    public final NumberPath<Integer> stHwId = createNumber("stHwId", Integer.class);

    public QStudentHomeworkFile(String variable) {
        super(StudentHomeworkFile.class, forVariable(variable));
    }

    public QStudentHomeworkFile(Path<? extends StudentHomeworkFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudentHomeworkFile(PathMetadata metadata) {
        super(StudentHomeworkFile.class, metadata);
    }

}

