package com.ssafy.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class NoticeFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "file_id")
    Integer fileId;

    @Column(name= "notice_id")
    Integer noticeId;

    @Column(name= "file_origin_name")
    String fileOriginName;

    @Column(name= "file_name")
    String fileName;

    @Column(name= "file_path")
    String filePath;

    @Column(name= "file_ext")
    String fileExt;

    @Column(name = "file_size")
    String fileSize;

//    @ManyToOne
//    @JoinColumn(name = "notice_id",insertable = false, updatable = false)
//    private Notice notice;
}
