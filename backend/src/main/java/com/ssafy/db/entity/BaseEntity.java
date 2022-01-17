package com.ssafy.db.entity; //DB table과 매핑돠는 Entity Class

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 모델 간 공통 사항 정의.
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity { //공통 Entity 속성 정의
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id = null;
}
