package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
public class Teacher implements Serializable { //Teacher Table 속성 정의
    @Id
    @Column(name = "tchr_id")
    String tchrId;
    @Column(name = "tchr_name")
    String tchrName;
    @Column(name = "tchr_email")
    String tchrEmail;
    @Column(name = "tchr_contact")
    String tchrContact;
    @Column(name = "tchr_school")
    String tchrSchool;
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "tchr_password")
    String tchrPassword;
}
