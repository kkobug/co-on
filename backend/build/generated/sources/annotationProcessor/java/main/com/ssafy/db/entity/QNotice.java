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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotice notice = new QNotice("notice");

    public final StringPath noticeContent = createString("noticeContent");

    public final ListPath<NoticeFile, QNoticeFile> noticeFile = this.<NoticeFile, QNoticeFile>createList("noticeFile", NoticeFile.class, QNoticeFile.class, PathInits.DIRECT2);

    public final NumberPath<Integer> noticeId = createNumber("noticeId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> noticePosted = createDateTime("noticePosted", java.time.LocalDateTime.class);

    public final StringPath noticeTitle = createString("noticeTitle");

    public final NumberPath<Integer> studyId = createNumber("studyId", Integer.class);

    public final QStudyroom studyroom;

    public final StringPath tchrId = createString("tchrId");

    public QNotice(String variable) {
        this(Notice.class, forVariable(variable), INITS);
    }

    public QNotice(Path<? extends Notice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNotice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNotice(PathMetadata metadata, PathInits inits) {
        this(Notice.class, metadata, inits);
    }

    public QNotice(Class<? extends Notice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyroom = inits.isInitialized("studyroom") ? new QStudyroom(forProperty("studyroom")) : null;
    }

}

