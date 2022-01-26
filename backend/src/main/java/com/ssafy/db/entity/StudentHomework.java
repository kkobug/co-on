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
public class StudentHomework implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "st_hwid", updatable = false)
    int stHwId;
    @Column(name = "hw_id", updatable = false)
    int hwId;
    @Column(name = "study_id", updatable = false)
    int studyId;
    @Column(name = "st_hwscore")
    int stHwscore;
    @Column(name = "tchr_id", updatable = false)
    String tchrId;
    @Column(name = "st_id", updatable = false)
    String stId;
    @Column(name = "st_hwcontent")
    String stHwcontent;

    @CreationTimestamp
    @LastModifiedDate
    @Column(name = "st_hwposted")
    LocalDateTime stHwposted;
//    @Column(name = "st_hwfile")
//    파일 업로드 필요함!!!
}
