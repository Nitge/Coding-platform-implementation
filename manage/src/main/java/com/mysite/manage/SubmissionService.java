package com.mysite.manage;

import com.mysite.manage.exception.NotFoundException;
import com.mysite.manage.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    Submission checkSubmission(String username, String password, int id){
        Optional<Submission> opsub =
        submissionRepository.findByUsernameAndPasswordAndId(username, password, id);
        if(opsub.isPresent()){
            return opsub.get();
        }
        else{
            opsub = submissionRepository.findByUsernameAndId(username, id);
            if(opsub.isPresent()){
                throw new UnauthorizedException(); //패스워드 불일치 : 401
            }
            else{
                throw new NotFoundException(); //db에 일치하는 데이터 없음 : 404
            }
        }
    }

    int createSubmission(SubmissionDTO submissionDTO) {

        Submission submission = new Submission(submissionDTO);
        submissionRepository.save(submission);
        return submission.getId();
    }

    void updateSubmission(UpdateDTO updateDTO) {
        Optional<Submission> opsub = submissionRepository.findByIdAndStatus(
                updateDTO.getId(), "PROCESSING");
        if(opsub.isPresent()){
            Submission submission = opsub.get();
            submission.updateStatus(updateDTO.getStatus());
            submissionRepository.save(submission);
        }
        else{
            throw new NotFoundException(); //db에 일치하는 데이터 없음 : 404
        }
    }
}
