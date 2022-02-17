package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNoticeFile is a Querydsl query type for NoticeFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNoticeFile extends EntityPathBase<NoticeFile> {

    private static final long serialVersionUID = 1672054198L;

    public static final QNoticeFile noticeFile = new QNoticeFile("noticeFile");

    public final StringPath fileExt = createString("fileExt");

    public final NumberPath<Integer> fileId = createNumber("fileId", Integer.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath fileOriginName = createString("fileOriginName");

    public final StringPath filePath = createString("filePath");

    public final StringPath fileSize = createString("fileSize");

    public final NumberPath<Integer> noticeId = createNumber("noticeId", Integer.class);

    public QNoticeFile(String variable) {
        super(NoticeFile.class, forVariable(variable));
    }

    public QNoticeFile(Path<? extends NoticeFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNoticeFile(PathMetadata metadata) {
        super(NoticeFile.class, metadata);
    }

}

