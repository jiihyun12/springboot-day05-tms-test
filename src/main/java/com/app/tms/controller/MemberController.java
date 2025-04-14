package com.app.tms.controller;


import com.app.tms.domain.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.app.tms.mapper.MemberMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import com.app.tms.service.MemberService;

@Controller
@Slf4j
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberMapper memberMapper;
    private final HttpSession session;
    private final MemberService memberService;

//    회원 가입
    @GetMapping("join")
    public void goToJoinForm(MemberVO memberVO){;}


    @PostMapping("join")
    public RedirectView join(MemberVO memberVO){

        memberMapper.insert(memberVO);

        return new RedirectView("/member/login");
    }
}
