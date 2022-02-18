package com.ssafy.db.repository.conference;

import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer> {

//    @Query(value = "select attendance_record.* from attendance_record " +
//            "where st_Id=:stId and conf_Id=:confId and rec_out is null limit 1",
//            nativeQuery = true)
//    AttendanceRecord findAttendanceRecordByIds(String stId, Integer confId);
}
