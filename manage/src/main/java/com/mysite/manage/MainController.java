package com.mysite.manage;

import com.mysite.manage.file.FileService;
import com.mysite.manage.submission.Submission;
import com.mysite.manage.submission.SubmissionDTO;
import com.mysite.manage.submission.SubmissionService;
import com.mysite.manage.submission.UpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/manage")
@RequiredArgsConstructor
public class MainController {

    private final SubmissionService submissionService;
    private final FileService fileService;

    @GetMapping("/submission")
    public ResponseEntity<Submission> getResult(@RequestParam String username, @RequestParam String password, @RequestParam int id) {
        return new ResponseEntity<Submission>(submissionService.checkSubmission(username, password, id), HttpStatus.OK);
        //TODO: 패스워드만 일치하지 않을 경우(완료)
        //TODO: 일치하는 항목이 없는 경우(완료)
    }
    
    @PostMapping("/submission")
    public ResponseEntity<Map<String,Integer>> codeSubmission(@RequestBody SubmissionDTO dto){
        //TODO: 코드 .py 파일로 변환 후 저장(완료)
        Integer id = submissionService.createSubmission(dto);
        fileService.saveFile(id, dto.getCode());
        Map<String,Integer> map = new HashMap<>();
        map.put("id", id);
        return new ResponseEntity<Map<String,Integer>>(map, HttpStatus.OK);
    }
    
    //TODO: exec 서버와의 API 작성
    //TODO: GET 요청시 FTP로 id.py 넘겨주기
    @GetMapping("/new")
    public void getSubmission(){

    }

    @PatchMapping("/submission")
    public void updateSubmission(@RequestBody UpdateDTO dto){
        submissionService.updateSubmission(dto);
    }
}
