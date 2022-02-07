package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "att_id", updatable = false)
    Integer attId;
    @Column(name = "conf_id", updatable = false)
    Integer confId;
    @Column(name = "st_id", updatable = false)
    String stId;
    @Column(name = "att_pass")
    Boolean attPass;
}
