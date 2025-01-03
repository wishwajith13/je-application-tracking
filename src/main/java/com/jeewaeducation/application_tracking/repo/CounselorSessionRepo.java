package com.jeewaeducation.application_tracking.repo;

import com.jeewaeducation.application_tracking.entity.CounselorSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CounselorSessionRepo extends JpaRepository<CounselorSession, Integer> {
}
