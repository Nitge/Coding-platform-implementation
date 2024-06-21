package com.mysite.manage.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member register(Member member) {
        if (memberRepository.findByUsername(member.getUsername()) == null) {
            return memberRepository.save(member);
        } else {
            return null; // 이미 등록된 사용자
        }
    }

    public boolean login(String username, String password) {
        Member member = memberRepository.findByUsername(username);
        return member != null && member.getPassword().equals(password);
    }
}