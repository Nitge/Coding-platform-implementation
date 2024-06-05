package com.mysite.manage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    Submission findByUsernameAndPasswordAndId(String username, String password, Integer id);
}
