package com.mysite.manage.user;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private LocalDateTime updated_at;

    private LocalDateTime created_at;

    public Member() {
        this.updated_at = LocalDateTime.now();
        this.created_at = LocalDateTime.now();
    }
}
