package com.ssafy.api.service.conference;

import com.ssafy.api.request.conference.ConferenceRegisterReq;
import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.Conference;

public interface ConferenceService {
    // 화상 개설
    Conference createConference(ConferenceRegisterReq conferenceRegisterReq);

    Conference findConference(Integer studyId, String tchrId);

    Attendance findAttendance(String stId, Integer confId);

    void enterConference(String stId, Integer confId);
}