package com.ssafy.api.service;

import com.ssafy.api.request.NoticeRegisterPostReq;
import com.ssafy.api.request.NoticeUpdatePutReq;
import com.ssafy.db.entity.Homework;
import com.ssafy.db.entity.Notice;
import com.ssafy.db.repository.NoticeRepository;
import com.ssafy.db.repository.NoticeRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    NoticeRepositorySupport noticeRepositorySupport;


    @Override
    public Notice createNotice(NoticeRegisterPostReq noticeRegisterPostReq) {
        Notice notice = new Notice();
        notice.setNoticeId(noticeRegisterPostReq.getNoticeId());
        notice.setStudyId(noticeRegisterPostReq.getStudyId());
        notice.setTchrId(noticeRegisterPostReq.getTchrId());
        notice.setNoticeTitle(noticeRegisterPostReq.getNoticeTitle());
        notice.setNoticeContent(noticeRegisterPostReq.getNoticeContent());
        notice.setNoticePosted(LocalDateTime.now());        // date 타입으로 필요 시 String.valueOf 제거
        return noticeRepository.save(notice);
    }

    @Override
    public void deleteNotice(int noticeId, String tchrId) {
        noticeRepositorySupport.deleteNoticeByNoticeIdAndTchrId(noticeId, tchrId);
    }

    @Override
    public Notice updateNotice(NoticeUpdatePutReq noticeUpdatePutReq) {
        Notice notice = new Notice();
        notice.setNoticeId(noticeUpdatePutReq.getNoticeId());
        notice.setStudyId(noticeUpdatePutReq.getStudyId());
        notice.setTchrId(noticeUpdatePutReq.getTchrId());
        notice.setNoticeTitle(noticeUpdatePutReq.getNoticeTitle());
        notice.setNoticeContent(noticeUpdatePutReq.getNoticeContent());
        notice.setNoticePosted(LocalDateTime.now());
        return noticeRepository.save(notice);
    }

    @Override
    public List<Notice> findNoticeByStudyId(Integer studyId) {
        return noticeRepositorySupport.findNoticeByStudyId(studyId);
    }

    @Override
    public List<Notice> findNoticeByTchrId(String tchrId) {
        return noticeRepositorySupport.findNoticeByTchrId(tchrId);
    }


    @Override
    public Notice findBynoticeId(int NoticeId) {
        Notice notice = noticeRepositorySupport.findByNoticeId(NoticeId).get();
        return notice;
    }

}
