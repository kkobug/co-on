package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
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
    @Column(name= "notice_title")
    String noticeTitle;
    @Column(name= "notice_content")
    String noticeContent;

    //생성/수정일자 자동 갱신
    @CreationTimestamp
    @LastModifiedDate
    @Column(name= "notice_posted")
    LocalDateTime noticePosted;
}
