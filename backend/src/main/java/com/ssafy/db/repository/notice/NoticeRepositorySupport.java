package com.ssafy.db.repository.notice;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Notice;
import com.ssafy.db.entity.QNotice;
import com.ssafy.db.entity.QStudyroom;
import com.ssafy.db.entity.QStudyroomDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class NoticeRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QNotice qnotice = QNotice.notice;
    QStudyroomDetail qStudyroomDetail = QStudyroomDetail.studyroomDetail;
    QStudyroom qStudyroom = QStudyroom.studyroom;

    // 수업 ID로 공지 조회 (특정 수업에서 발생한 모든 공지)
    public List<Notice> findNoticeByStudyId(Integer studyId){
        return jpaQueryFactory.select(qnotice).from(qnotice)
                .where(qnotice.studyId.eq(studyId))
                .orderBy(qnotice.noticePosted.desc())
                .fetch();
    }

    // 교사 ID로 공지 조회 (교사가 작성한 모든 공지)
    public List<Notice> findNoticeByTchrId(String tchrId) {
        return jpaQueryFactory.select(qnotice).from(qnotice)
                .where(qnotice.tchrId.eq(tchrId))
                .orderBy(qnotice.noticePosted.desc())
                .fetch();
    }

    // 공지 ID로 공지 조회 (단일 공지 상세내역 조회)
    public Optional<Notice> findByNoticeId(Integer noticeId){
        Notice notice = jpaQueryFactory.select(qnotice).from(qnotice)
                .where(qnotice.noticeId.eq(noticeId)).fetchOne();
        return Optional.ofNullable(notice);
    }

    // 공지 삭제
    public void deleteNoticeByNoticeIdAndTchrId(Integer noticeId, String tchrId) {
        jpaQueryFactory.delete(qnotice).where(qnotice.noticeId.eq(noticeId).and(qnotice.tchrId.eq(tchrId))).execute();  //excute 추가
    }
    // 학생 ID로 모든 공지 조회 (학생이 속해있는 모든 수업에서 발생한 공지 조회)
    public List<Notice> findNoticeBystId(String stId){
        return jpaQueryFactory.selectFrom(qnotice).orderBy(qnotice.noticePosted.desc()).leftJoin(qStudyroomDetail).on(qnotice.studyId.eq(qStudyroomDetail.studyId))
                .leftJoin(qStudyroom).on(qStudyroomDetail.studyId.eq(qStudyroom.studyId))
                .where(qStudyroomDetail.stId.eq(stId)).fetch();
    }
}
