package com.ssafy.api.response;

import com.ssafy.db.entity.Student;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudentResponse")
public class StudentFindID {
    @ApiModelProperty(name="Student ID")
    String stId;
    @ApiModelProperty(name="Student email")
    String stEmail;
    @ApiModelProperty(name="Student concat")
    String stConcat;
    @ApiModelProperty(name="Student birthday")
    String stBirthday;
    @ApiModelProperty(name="Student school")
    String stSchool;
    public static StudentFindID of(Student student){
        StudentFindID res = new StudentFindID();
        res.setStId(student.getStId());
        res.setStEmail(student.getStEmail());
        res.setStConcat(student.getStContact());
        res.setStBirthday(student.getStBirthday());
        res.setStSchool(student.getStSchool());
        return res;
    }
}
