package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
=======
import javax.persistence.*;
>>>>>>> feature/loginteach
import java.io.Serializable;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
public class Teacher implements Serializable {          // point 1
    @Id
    @Column(name = "tchr_id")
    String tchrId;
<<<<<<< HEAD
    @Column(name = "tchr_name")
=======
    @Column(name = "tchr_name")             //point 2
>>>>>>> feature/loginteach
    String tchrName;
    @Column(name = "tchr_email")
    String tchrEmail;
    @Column(name = "tchr_contact")
    String tchrContact;
    @Column(name = "tchr_school")
    String tchrSchool;
<<<<<<< HEAD
=======

>>>>>>> feature/loginteach
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "tchr_password")
    String tchrPassword;
}
