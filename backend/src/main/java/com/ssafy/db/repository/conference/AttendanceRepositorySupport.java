package com.ssafy.db.repository.conference;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttendanceRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QAttendance qAttendance = QAttendance.attendance;
    QAttendanceRecord qAttendanceRecord = QAttendanceRecord.attendanceRecord;

    public List<AttendanceRecord> findAttendanceRecord(String stId, int confId){
        return jpaQueryFactory.select(qAttendanceRecord).from(qAttendanceRecord).where(qAttendanceRecord.stId.eq(stId).and(qAttendanceRecord.confId.eq(confId))).fetch();
    }

    //fetchFirst -> 조회 대상이 1건이든 1건이상이든 무조건 1건만 반환
    public AttendanceRecord findAttendanceRecordByIds(String stId, Integer confId){
        return jpaQueryFactory.selectFrom(qAttendanceRecord).where(qAttendanceRecord.stId.eq(stId).and(qAttendanceRecord.confId.eq(confId)).and(qAttendanceRecord.recOut.isNull())).fetchFirst();
    }

}
