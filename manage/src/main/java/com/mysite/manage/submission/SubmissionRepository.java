package com.mysite.manage.submission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    public Optional<Submission> findByUsernameAndPasswordAndId(String username, String password, Integer id);
    public Optional<Submission> findByUsernameAndId(String username, Integer id);
    public Optional<Submission> findByIdAndStatus(Integer id, String status);
}
