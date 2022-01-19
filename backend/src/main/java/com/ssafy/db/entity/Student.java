package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Student implements Serializable{ //Student Table 속성 정의
    @Id
    @Column(name = "st_id")
    String stId;
    @Column(name = "st_name")
    String stName;
    @Column(name = "st_email")
    String stEmail;
    @Column(name = "st_contact")
    String stContact;
    @Column(name = "st_school")
    String stSchool;
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "st_password")
    String stPassword;
}