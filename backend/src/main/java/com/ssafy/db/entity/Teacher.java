package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

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
    String tchr_id;
    String tchr_name;
    String tchr_email;
    String tchr_contact;
    String tchr_signup_date;
    String tchr_school;
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String tchr_password;
}
