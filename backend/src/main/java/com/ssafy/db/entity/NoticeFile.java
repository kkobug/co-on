package com.ssafy.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@NoArgsConstructor
public class NoticeFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "file_id")
    Long fileId;

    @Column(name= "notice_id")
    Long noticeId;

    @Column(name= "file_origin_name")
    String fileOriginName;

    @Column(name= "file_name")
    String fileName;

    @Column(name= "file_path")
    String filePath;

//    @Column(name= "ext")
//    String ext;
//
//    @Column(name= "size")
//    String size;
//
//    @Column(name= "content_type")
//    String contentType;
}
