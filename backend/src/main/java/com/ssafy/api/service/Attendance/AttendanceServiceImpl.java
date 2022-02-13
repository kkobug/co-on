package com.ssafy.api.service.Attendance;

import com.ssafy.db.repository.conference.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AttendanceService")
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceRepository attendanceRepository;
    @Override
    @Cacheable(value = "countStudentAtt", key="#stId")  //10분에 한번조회
    public List<int[]> countStudentAttendance(String stId) {
        List<int[]> arr = attendanceRepository.countStudentAttendance(stId);
        System.out.println("countStudentAttendance 실행 ................................."+stId);
        return arr;
    }

    @Override
    @Cacheable(value = "countTchrtAtt", key="#tchrId") //10분에 한번조회
    public List<int[]> countAttendance(String tchrId) {
        List<int[]> list = attendanceRepository.countAttendance(tchrId);
        return list;
    }
}
