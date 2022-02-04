package com.ssafy.api.service.conference;

import com.ssafy.api.request.conference.ConferenceRegisterReq;
import com.ssafy.db.entity.Conference;

public interface ConferenceService {
    // 화상 개설
    Conference createConference(ConferenceRegisterReq conferenceRegisterReq);

    Conference findConference(Integer studyId, String tchrId);

    void enterConference();
}
