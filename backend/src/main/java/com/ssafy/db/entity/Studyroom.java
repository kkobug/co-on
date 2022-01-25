package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Studyroom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "study_id")
    int studyId;
    @Column(name= "tchr_id")
    String tchrId;
    @Column(name= "study_name")
    String studyName;
    @Column(name= "study_desc")
    String studyDesc;
<<<<<<< Updated upstream
    @ManyToOne
    @JoinColumn(name = "tchr_id",insertable = false, updatable = false)
    private Teacher teacher;
=======
//
//    @ManyToOne
//    @JoinColumn(name = "tchr_id",insertable = false, updatable = false)
//    private Teacher teacher;
>>>>>>> Stashed changes
}
