package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Notice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "notice_id", updatable = false)
    int noticeId;
    @Column(name= "study_id", updatable = false)
    int studyId;
    @Column(name= "tchr_id", updatable = false)
    String tchrId;
    @Column(name= "notice_title", updatable = false)
    String noticeTitle;
    @Column(name= "notice_content", updatable = false)
    String noticeContent;

    //생성/수정일자 자동 갱신
    @CreatedDate
    @LastModifiedDate
    @Column(name= "notice_posted", updatable = false)
    LocalDateTime noticePosted;
}
