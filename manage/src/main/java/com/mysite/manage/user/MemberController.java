package com.mysite.manage.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public String register(@RequestBody Member member) {
        if (memberService.register(member) != null) {
            return "Registration successful";
        } else {
            return "Registration failed: Username already exists";
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody Member member) {
        if (memberService.login(member.getUsername(), member.getPassword())) {
            return "Login successful";
        } else {
            return "Login failed";
        }
    }
}