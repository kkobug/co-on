package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyroomDetail is a Querydsl query type for StudyroomDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyroomDetail extends EntityPathBase<StudyroomDetail> {

    private static final long serialVersionUID = -4775021L;

    public static final QStudyroomDetail studyroomDetail = new QStudyroomDetail("studyroomDetail");

    public final StringPath stId = createString("stId");

    public final NumberPath<Integer> stPoint = createNumber("stPoint", Integer.class);

    public final NumberPath<Integer> studyId = createNumber("studyId", Integer.class);

    public final NumberPath<Integer> studyroomDetailId = createNumber("studyroomDetailId", Integer.class);

    public final StringPath tchrId = createString("tchrId");

    public QStudyroomDetail(String variable) {
        super(StudyroomDetail.class, forVariable(variable));
    }

    public QStudyroomDetail(Path<? extends StudyroomDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyroomDetail(PathMetadata metadata) {
        super(StudyroomDetail.class, metadata);
    }

}

