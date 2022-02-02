package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class HomeworkFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "file_id")
    Long fileId;

    @Column(name= "notice_id")
    Integer hwId;

    @Column(name= "file_origin_name")
    String fileOriginName;

    @Column(name= "file_name")
    String fileName;

    @Column(name= "file_path")
    String filePath;
}
