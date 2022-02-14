package com.ssafy.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("findById"),             //사용자 정보 조회 StudentServiceImpl, TeacherServiceImpl 유저 정보 수정, 탈퇴 시 캐시 변경
                new ConcurrentMapCache("hwDetail"),             //과제 상세 내용 HomeworkServiceImpl 과제 수정, 삭제시 캐시 변경
                new ConcurrentMapCache("HwRateBystId"),         //학생 과제 제출률 HomeworkServiceImpl  10분마다 다시 캐시
                new ConcurrentMapCache("HwRateBytchrId"),       //교사 과제 제출률 확인 HomeworkServiceImpl 10분마다 다시 캐시
                new ConcurrentMapCache("findNotice"),           //공지사항 상세조회 NoticeServiceImpl 공지사항 삭제, 수정 시 캐시 변경
                new ConcurrentMapCache("findStudent"),          //우리반 조회 StudyroomDetailServiceImpl 학생 추가, 삭제 시 캐시 변경
                new ConcurrentMapCache("findStudyroomBystId"),  //학생 수업 조회 StudyroomDetailServiceImpl 학생수업 조회 학생 추가, 삭제시 캐시 삭제
                new ConcurrentMapCache("TchrClassList"),        //교사 수업 조회 StudyRoomServiceImpl 수업 추가, 삭제 시 캐시 변경
                new ConcurrentMapCache("countStudentAtt"),      //학생 수업 출석률 AttendanceServiceImpl 10분마다 다시 캐시, 학생 삭제시 캐시 삭제
                new ConcurrentMapCache("countTchrtAtt"),        //교사 수업 출석률 확인 AttendanceServiceImpl 10분마다 다시 캐시
                new ConcurrentMapCache("findStHw"),             //학생과제 세부 조회 StudentHomeworkServiceImpl 10분마다 다시 캐시
                new ConcurrentMapCache("findSubmittedHw")       //학생본인이 제출한 과제 조회 StudentHomeworkServiceImpl 과제 제출,삭제 시 캐시 변경
        ));
        return cacheManager;
    }
}
