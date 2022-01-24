package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Homework implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hw_id")
    int hwId;
    @Column(name = "study_id")
    int studyId;
    @Column(name = "tchr_id")
    String tchrId;
    @Column(name = "hw_title")
    String hwTitle;
    @Column(name = "hw_content")
    String hwContent;
    @Column(name = "hw_deadline")
    String hwDeadline;
    @ManyToOne
    @JoinColumn(name = "study_id", insertable = false, updatable = false)
    private Studyroom studyroom;
//    파일 업로드, tchr_id 필드 필요함!!!
}
