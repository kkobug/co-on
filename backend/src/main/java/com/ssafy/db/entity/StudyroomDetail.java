package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class StudyroomDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "studyroom_detail_id", updatable = false)
    Integer studyroomDetailId;
    @Column(name = "study_id")
    Integer studyId;
    @Column(name = "tchr_id")
    String tchrId;
    @Column(name ="st_id")
    String stId;
    @Column(name ="st_point")
    Integer stPoint;

    @ManyToOne
    @JoinColumn(name = "study_id",insertable = false,updatable = false)
    private Studyroom studyroom;
}
