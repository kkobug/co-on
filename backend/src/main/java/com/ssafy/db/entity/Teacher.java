package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Teacher implements Serializable {          // point 1
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
    @Column(name = "tchr_birthday")
    String tchrBirthday;
    @Column(name = "tchr_img")
    String tchrImg;
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "tchr_password")
    String tchrPassword;
}
