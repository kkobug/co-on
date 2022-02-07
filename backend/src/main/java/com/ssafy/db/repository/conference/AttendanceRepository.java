package com.ssafy.db.repository.conference;

import com.ssafy.db.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    @Query(value = "SELECT attendance.* FROM attendance " +
            "WHERE st_Id=:stId AND conf_Id=:confId",
    nativeQuery = true)
    Attendance findAttendanceByIds(String stId, Integer confId);
}
