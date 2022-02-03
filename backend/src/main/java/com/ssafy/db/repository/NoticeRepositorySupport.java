package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Notice;
import com.ssafy.db.entity.QNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class NoticeRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QNotice qnotice = QNotice.notice;

    // 수업 ID로 공지 조회 (특정 수업에서 발생한 모든 공지)
    public List<Notice> findNoticeByStudyId(Integer studyId){
        List<Notice> list = jpaQueryFactory.select(qnotice).from(qnotice)
                .where(qnotice.studyId.eq(studyId)).fetch();
        return list;
    }

    // 교사 ID로 공지 조회 (교사가 작성한 모든 공지)
    public List<Notice> findNoticeByTchrId(String tchrId) {
        List<Notice> list = jpaQueryFactory.select(qnotice).from(qnotice)
                .where(qnotice.tchrId.eq(tchrId)).fetch();
        return list;
    }

    // 공지 ID로 공지 조회 (단일 공지 상세내역 조회)
    public Optional<Notice> findByNoticeId(long noticeId){
        Notice notice = jpaQueryFactory.select(qnotice).from(qnotice)
                .where(qnotice.noticeId.eq(noticeId)).fetchOne();
        if(notice == null) return Optional.empty();
        return Optional.ofNullable(notice);
    }

    // 공지 삭제
    public void deleteNoticeByNoticeIdAndTchrId(long noticeId, String tchrId) {
        jpaQueryFactory.delete(qnotice).where(qnotice.noticeId.eq(noticeId).and(qnotice.tchrId.eq(tchrId))).execute();  //excute 추가
    }
}
