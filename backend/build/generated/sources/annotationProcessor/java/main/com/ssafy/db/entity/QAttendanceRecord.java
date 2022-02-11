package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttendanceRecord is a Querydsl query type for AttendanceRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAttendanceRecord extends EntityPathBase<AttendanceRecord> {

    private static final long serialVersionUID = 1606934620L;

    public static final QAttendanceRecord attendanceRecord = new QAttendanceRecord("attendanceRecord");

    public final NumberPath<Integer> attId = createNumber("attId", Integer.class);

    public final NumberPath<Integer> confId = createNumber("confId", Integer.class);

    public final NumberPath<Integer> recId = createNumber("recId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> recIn = createDateTime("recIn", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> recOut = createDateTime("recOut", java.time.LocalDateTime.class);

    public final StringPath stId = createString("stId");

    public QAttendanceRecord(String variable) {
        super(AttendanceRecord.class, forVariable(variable));
    }

    public QAttendanceRecord(Path<? extends AttendanceRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttendanceRecord(PathMetadata metadata) {
        super(AttendanceRecord.class, metadata);
    }

}

