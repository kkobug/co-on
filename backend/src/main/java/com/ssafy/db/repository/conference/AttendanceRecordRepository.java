package com.ssafy.db.repository.conference;

import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer> {

    @Query(value = "SELECT attendance_record.* FROM attendance_record " +
            "WHERE rec_out IS NULL LIMIT 1",
            nativeQuery = true)
    AttendanceRecord findAttendanceRecordByIds(String stId, Integer confId);
}
