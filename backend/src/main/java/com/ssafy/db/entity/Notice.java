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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "notice_id")
    int noticeId;
    @Column(name= "study_id")
    int studyId;
    @Column(name= "tchr_id")
    String tchrId;
    @Column(name= "notice_title")
    String noticeTitle;
    @Column(name= "notice_content")
    String noticeContent;

    //생성/수정일자 자동 갱신
    @CreatedDate
    @LastModifiedDate
    @Column(name= "notice_posted")
    private LocalDateTime noticePosted;
}
