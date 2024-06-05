package com.mysite.manage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    Submission checkSubmission(String username, String password, int id){
         return submissionRepository.findByUsernameAndPasswordAndId(username, password, id);
    }

    int createSubmission(SubmissionDTO submissionDTO) {
        Submission submission = new Submission(submissionDTO);
        submissionRepository.save(submission);
        return submission.getId();
    }
}
