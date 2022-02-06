package com.ssafy.api.service.conference;

import com.ssafy.api.request.conference.ConferenceRegisterReq;
import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.AttendanceRecord;
import com.ssafy.db.entity.Conference;
import com.ssafy.db.repository.conference.AttendanceRecordRepository;
import com.ssafy.db.repository.conference.AttendanceRepository;
import com.ssafy.db.repository.conference.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("ConferenceService")
public class ConferenceServiceImpl implements ConferenceService{
    @Autowired
    ConferenceRepository conferenceRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    AttendanceRecordRepository attendanceRecordRepository;

    @Override
    public Conference createConference(ConferenceRegisterReq conferenceRegisterReq) {
        Conference conference = new Conference();

        LocalDateTime now = LocalDateTime.now();

        String[] confst, confed;
        confst = conferenceRegisterReq.getConfStart().split(":");
        confed = conferenceRegisterReq.getConfEnd().split(":");

        LocalDateTime conferencestart = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), Integer.parseInt(confst[0]), Integer.parseInt(confst[1]));
        LocalDateTime conferenceend = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), Integer.parseInt(confed[0]), Integer.parseInt(confed[1]));

        conference.setStudyId(conferenceRegisterReq.getStudyId());
        conference.setTchrId(conferenceRegisterReq.getTchrId());
        conference.setConfTitle(conferenceRegisterReq.getConfTitle());
        conference.setConfStart(conferencestart);
        conference.setConfEnd(conferenceend);
        conference.setConfAtt(conferenceRegisterReq.getConfAtt());
        conferenceRepository.save(conference);
        return conference;
    }

    @Override
    public Conference findConference(Integer studyId, String tchrId) {
        return conferenceRepository.findConference(studyId, tchrId);
    }

    @Override
    public Attendance findAttendance(String stId, Integer confId) {
        return attendanceRepository.findAttendanceByIds(stId, confId);
    }

    @Override
    public AttendanceRecord findAttendanceRecord(String stId, Integer confId) {
        return attendanceRecordRepository.findAttendanceRecordByIds(stId, confId);
    }

    @Override
    public void enterConference(String stId, Integer confId) {
        Attendance attendance = findAttendance(stId, confId);
        LocalDateTime now = LocalDateTime.now();

        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setAttId(attendance.getAttId());
        attendanceRecord.setStId(stId);
        attendanceRecord.setConfId(confId);
        attendanceRecord.setRecIn(now);

        attendanceRecordRepository.save(attendanceRecord);
    }

    @Override
    public void exitConference(String stId, Integer confId) {
        AttendanceRecord attendanceRecord = findAttendanceRecord(stId, confId);
        LocalDateTime now = LocalDateTime.now();

        attendanceRecord.setRecOut(now);

        attendanceRecordRepository.save(attendanceRecord);
    };

}
