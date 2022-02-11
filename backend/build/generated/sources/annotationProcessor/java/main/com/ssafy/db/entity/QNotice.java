package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotice is a Querydsl query type for Notice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNotice extends EntityPathBase<Notice> {

    private static final long serialVersionUID = 1574844698L;

    public static final QNotice notice = new QNotice("notice");

    public final StringPath noticeContent = createString("noticeContent");

    public final ListPath<NoticeFile, QNoticeFile> noticeFile = this.<NoticeFile, QNoticeFile>createList("noticeFile", NoticeFile.class, QNoticeFile.class, PathInits.DIRECT2);

    public final NumberPath<Integer> noticeId = createNumber("noticeId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> noticePosted = createDateTime("noticePosted", java.time.LocalDateTime.class);

    public final StringPath noticeTitle = createString("noticeTitle");

    public final NumberPath<Integer> studyId = createNumber("studyId", Integer.class);

    public final StringPath tchrId = createString("tchrId");

    public QNotice(String variable) {
        super(Notice.class, forVariable(variable));
    }

    public QNotice(Path<? extends Notice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotice(PathMetadata metadata) {
        super(Notice.class, metadata);
    }

}

