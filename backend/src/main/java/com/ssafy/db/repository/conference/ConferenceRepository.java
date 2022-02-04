package com.ssafy.db.repository.conference;

import com.ssafy.db.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

    @Query(value = "SELECT conference.* FROM conference WHERE tchr_id=:tchrId AND study_id=:studyId ORDER BY conf_id DESC LIMIT 1"
            ,nativeQuery = true)
    Conference findConference(Integer studyId, String tchrId);

}
