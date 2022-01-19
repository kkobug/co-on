package com.ssafy.api.response;

import com.ssafy.db.entity.Student;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("StudentResponse")
public class StudentRes {
    @ApiModelProperty(name="Student ID")
    String stId;
    public static StudentRes of(Student student){
        StudentRes res = new StudentRes();
        res.setStId(student.getStId());
        return res;
    }
}
