package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConference is a Querydsl query type for Conference
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConference extends EntityPathBase<Conference> {

    private static final long serialVersionUID = -625543586L;

    public static final QConference conference = new QConference("conference");

    public final ListPath<Attendance, QAttendance> attendances = this.<Attendance, QAttendance>createList("attendances", Attendance.class, QAttendance.class, PathInits.DIRECT2);

    public final NumberPath<Integer> confAtt = createNumber("confAtt", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> confDes = createDateTime("confDes", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> confEnd = createDateTime("confEnd", java.time.LocalDateTime.class);

    public final NumberPath<Integer> confId = createNumber("confId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> confInit = createDateTime("confInit", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> confStart = createDateTime("confStart", java.time.LocalDateTime.class);

    public final StringPath confTitle = createString("confTitle");

    public final NumberPath<Integer> studyId = createNumber("studyId", Integer.class);

    public final StringPath tchrId = createString("tchrId");

    public QConference(String variable) {
        super(Conference.class, forVariable(variable));
    }

    public QConference(Path<? extends Conference> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConference(PathMetadata metadata) {
        super(Conference.class, metadata);
    }

}

