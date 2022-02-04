package com.ssafy.api.service.conference;

import com.ssafy.api.request.conference.ConferenceRegisterReq;
import com.ssafy.db.entity.Conference;
import com.ssafy.db.repository.conference.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ConferenceService")
public class ConferenceServiceImpl implements ConferenceService{
    @Autowired
    ConferenceRepository conferenceRepository;

    @Override
    public Conference createConference(ConferenceRegisterReq conferenceRegisterReq) {
        Conference conference = new Conference();
        conference.setStudyId(conferenceRegisterReq.getStudyId());
        conference.setTchrId(conferenceRegisterReq.getTchrId());
        conference.setConfTitle(conferenceRegisterReq.getConfTitle());
        conference.setConfStart(conferenceRegisterReq.getConfStart());
        conference.setConfEnd(conferenceRegisterReq.getConfEnd());
        conference.setConfAtt(conferenceRegisterReq.getConfAtt());
        conferenceRepository.save(conference);
        return conference;
    }

    @Override
    public Conference findConference(Integer studyId, String tchrId) {
        return conferenceRepository.findConference(studyId, tchrId);
    };


    @Override
    public void enterConference() {

    }
}
