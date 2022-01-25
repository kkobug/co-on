package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Homework implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hw_id", updatable = false)
    Integer hwId;
    @Column(name = "study_id", updatable = false)
    Integer studyId;
    @Column(name = "tchr_id", updatable = false)
    String tchrId;
    @Column(name = "hw_title")
    String hwTitle;
    @Column(name = "hw_content")
    String hwContent;
    @Column(name = "hw_deadline")
    String hwDeadline;
    @CreationTimestamp
    @Column(name = "hw_posted", updatable = false)
    LocalDateTime hwPosted;
//    @Column(name = "hw_file")
//    파일 업로드 필요함!!!
    @ManyToOne
    @JoinColumn(name = "study_id", insertable = false, updatable = false)
    private Studyroom studyroom;
}
