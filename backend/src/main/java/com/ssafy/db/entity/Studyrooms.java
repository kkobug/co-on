package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Studyrooms implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "studyroom_detail_id")
    int studyroomDetailId;
    @Column(name = "study_id")
    int studyId;
    @Column(name = "tchr_id")
    String tchrId;
    @Column(name ="st_id")
    String stId;
    @JoinColumn(name="st_name")
    String stName;

    @ManyToOne
    @JoinColumn(name = "student",insertable = false, updatable = false)
    private Student student;

}
