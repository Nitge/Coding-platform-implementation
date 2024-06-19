package com.mysite.manage.submission;

import com.mysite.manage.exception.BadRequestException;
import com.mysite.manage.exception.InternalServerErrorException;
import com.mysite.manage.exception.NotFoundException;
import com.mysite.manage.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    public Submission checkSubmission(String username, String password, int id){
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

    public int createSubmission(SubmissionDTO submissionDTO) {
        if(submissionDTO.getUsername() == null || submissionDTO.getPassword() == null || submissionDTO.getCode() == null){
            throw new BadRequestException();
        }
        Submission submission = new Submission(submissionDTO);
        submissionRepository.save(submission);
        return submission.getId();
    }

    public void updateSubmission(UpdateDTO updateDTO) {
        Optional<Submission> opsub = submissionRepository.findByIdAndStatus(
                updateDTO.getId(), "PROCESSING");
        if(opsub.isPresent()){
            Submission submission = opsub.get();
            submission.updateStatus(updateDTO.getStatus());
            submissionRepository.save(submission);
        }
        else{
            //throw new NotFoundException();
            throw new InternalServerErrorException(); //과제 조건 : 실패 시 500
        }
    }

    public Integer getEarliest(){
        Optional<Submission> opsub = submissionRepository.findAll()
                .stream()
                .filter(submission -> submission.getStatus().equals("SUBMITTED"))
                .min(Comparator.comparing(submission -> submission.getId()));
        if(opsub.isPresent()){
            Submission submission = opsub.get();
            submission.updateStatus("PROCESSING");
            submissionRepository.save(submission);
            return submission.getId();
        }
        else{
            throw new InternalServerErrorException();
        }
    }

}
