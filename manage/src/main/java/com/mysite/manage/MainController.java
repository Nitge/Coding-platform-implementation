package com.mysite.manage;

import com.mysite.manage.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
@RequiredArgsConstructor
public class MainController {

    private final SubmissionService submissionService;

    @GetMapping("/submission")
    public Submission getResult(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam int id) {
        return submissionService.checkSubmission(username, password, id);
        //TODO: 패스워드만 일치하지 않을 경우(완료)
        //TODO: 일치하는 항목이 없는 경우(완료)
    }
    
    @PostMapping("/submission")
    public int codeSubmission(@RequestBody SubmissionDTO dto){
        if(dto.getUsername() == null || dto.getPassword() == null || dto.getCode() == null){
            throw new BadRequestException();
        }
        //TODO: 코드 .py 파일로 변환 후 저장
        return submissionService.createSubmission(dto);
    }
    
    //TODO: exec 서버와의 API 작성
    @GetMapping("/new")
    public void getSubmission(){

    }

    @PatchMapping("/submission")
    public void updateSubmission(@RequestBody UpdateDTO dto){
        submissionService.updateSubmission(dto);
    }
}
