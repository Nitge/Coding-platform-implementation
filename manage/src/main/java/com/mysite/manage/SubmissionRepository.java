package com.mysite.manage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    Optional<Submission> findByUsernameAndPasswordAndId(String username, String password, Integer id);
    Optional<Submission> findByUsernameAndId(String username, Integer id);
    Optional<Submission> findByIdAndStatus(Integer id, String status);
}
