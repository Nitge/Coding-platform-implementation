package com.mysite.manage;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "SUBMISSION")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private LocalDateTime updated_at;

    private LocalDateTime created_at;

    private String status;

    Submission(SubmissionDTO dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
        this.status = "SUBMITTED";
    }

    //TODO : 상태 업데이트 함수 작성
}
