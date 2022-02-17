package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHomeworkFile is a Querydsl query type for HomeworkFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHomeworkFile extends EntityPathBase<HomeworkFile> {

    private static final long serialVersionUID = 1809272526L;

    public static final QHomeworkFile homeworkFile = new QHomeworkFile("homeworkFile");

    public final StringPath fileExt = createString("fileExt");

    public final NumberPath<Integer> fileId = createNumber("fileId", Integer.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath fileOriginName = createString("fileOriginName");

    public final StringPath filePath = createString("filePath");

    public final StringPath fileSize = createString("fileSize");

    public final NumberPath<Integer> hwId = createNumber("hwId", Integer.class);

    public QHomeworkFile(String variable) {
        super(HomeworkFile.class, forVariable(variable));
    }

    public QHomeworkFile(Path<? extends HomeworkFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHomeworkFile(PathMetadata metadata) {
        super(HomeworkFile.class, metadata);
    }

}

