package com.ssafy.common.auth;

import com.ssafy.api.service.user.TeacherService;
import com.ssafy.db.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 현재 액세스 토큰으로 부터 인증된 유저의 상세정보(활성화 여부, 만료, 롤 등) 관련 서비스 정의.
 */
@Component
public class SsafyTeacherDetailService implements UserDetailsService {
	@Autowired
	TeacherService teacherService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Teacher teacher = teacherService.findById(userId);
    		if(teacher != null) {
    			SsafyTeacherDetails teacherDetails = new SsafyTeacherDetails(teacher);
    			return teacherDetails;
    		}
    		return null;
    }
}
