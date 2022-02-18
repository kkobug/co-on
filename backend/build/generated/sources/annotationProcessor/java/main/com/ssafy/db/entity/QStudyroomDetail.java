package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyroomDetail is a Querydsl query type for StudyroomDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyroomDetail extends EntityPathBase<StudyroomDetail> {

    private static final long serialVersionUID = -4775021L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyroomDetail studyroomDetail = new QStudyroomDetail("studyroomDetail");

    public final StringPath stId = createString("stId");

    public final NumberPath<Integer> stPoint = createNumber("stPoint", Integer.class);

    public final NumberPath<Integer> studyId = createNumber("studyId", Integer.class);

    public final QStudyroom studyroom;

    public final NumberPath<Integer> studyroomDetailId = createNumber("studyroomDetailId", Integer.class);

    public final StringPath tchrId = createString("tchrId");

    public QStudyroomDetail(String variable) {
        this(StudyroomDetail.class, forVariable(variable), INITS);
    }

    public QStudyroomDetail(Path<? extends StudyroomDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyroomDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyroomDetail(PathMetadata metadata, PathInits inits) {
        this(StudyroomDetail.class, metadata, inits);
    }

    public QStudyroomDetail(Class<? extends StudyroomDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyroom = inits.isInitialized("studyroom") ? new QStudyroom(forProperty("studyroom")) : null;
    }

}

