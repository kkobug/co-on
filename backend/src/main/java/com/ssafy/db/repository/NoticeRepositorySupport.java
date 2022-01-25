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

    public Optional<Notice> findByNoticeId(int NoticeId){
        Notice notice = jpaQueryFactory.select(qnotice).from(qnotice)
                .where(qnotice.noticeId.eq(NoticeId)).fetchOne();
        if(notice == null) return Optional.empty();
        return Optional.ofNullable(notice);
    }

    public void deleteNoticeByNoticeIdAndTchrId(int noticeId, String tchrId) {
        jpaQueryFactory.delete(qnotice).where(qnotice.noticeId.eq(noticeId).and(qnotice.tchrId.eq(tchrId))).execute();  //excute 추가
    }
}
