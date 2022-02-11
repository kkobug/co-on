package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttendance is a Querydsl query type for Attendance
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAttendance extends EntityPathBase<Attendance> {

    private static final long serialVersionUID = 544183339L;

    public static final QAttendance attendance = new QAttendance("attendance");

    public final ListPath<AttendanceRecord, QAttendanceRecord> attendanceRecords = this.<AttendanceRecord, QAttendanceRecord>createList("attendanceRecords", AttendanceRecord.class, QAttendanceRecord.class, PathInits.DIRECT2);

    public final NumberPath<Integer> attId = createNumber("attId", Integer.class);

    public final NumberPath<Integer> attPass = createNumber("attPass", Integer.class);

    public final NumberPath<Integer> confId = createNumber("confId", Integer.class);

    public final StringPath stId = createString("stId");

    public QAttendance(String variable) {
        super(Attendance.class, forVariable(variable));
    }

    public QAttendance(Path<? extends Attendance> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttendance(PathMetadata metadata) {
        super(Attendance.class, metadata);
    }

}

