package com.app.tms.controller;

import com.app.tms.domain.MemberVO;
import com.app.tms.mapper.MemberMapper;
import com.app.tms.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller // 스프링에게 이 클래스가 웹 요청을 처리하는 컨트롤러임을 알려준다.
@RequestMapping("/member/*") // 이 컨트롤러의 모든 URL 앞에 /member/가 자동으로 붙는다.
@Slf4j // 로그를 찍을 수 있도록 log 객체를 자동 생성해주는 롬복 어노테이션
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 만들어주는 롬복 어노테이션
public class MemberController {
   private final MemberService memberService; // 회원 관련 비즈니스 로직을 처리하는 서비스 객체
   private final HttpSession session; // 로그인 상태를 유지하기 위한 세션 객체

   // 가입 페이지 이동
   @GetMapping("join") // '/member/join'으로 GET 요청이 들어오면 실행된다.
    public void goToJoin(MemberVO memberVO) {;} // 메서드 내부가 비어있고, 리턴값이 없으면
                                               // 요청 경로와 동일한 이름의 HTML 뷰로 자동 연결된다.

//    회원 가입 처리
    @PostMapping("join") // '/member/join'으로 POST 요청이 들어오면 실행된다.
    public RedirectView join(MemberVO memberVO) {
       memberService.join(memberVO); // 회원가입 정보 DB 저장 처리
       return new RedirectView("/member/login"); // 회원가입 후 로그인 페이지로 리다이렉트
    }

//    로그인 페이지 요청
    @GetMapping("login") // '/member/login'으로 GET 요청 시 로그인 폼 페이지로 이동한다.
    public void goToLogin(MemberVO memberVO) {;} // login.html 뷰 표시

//    로그인 처리
    @PostMapping("login") // '/member/login'으로 POST 요청 시 로그인 처리
    public RedirectView login(MemberVO memberVO, HttpSession session, RedirectAttributes redirectAttributes) {

      Optional<MemberVO> foundMember = memberService.select(memberVO); // // DB에서 이메일 + 비밀번호로 조회한다.
      if(foundMember.isPresent()) { // 회원 정보가 존재한다면 로그인 성공
        session.setAttribute("memberVO",foundMember.get() ); // 로그인 성공시 세션에 저장한다.
        return new RedirectView("/member/myhome"); // 마이 페이지로 이동
      }
      return new RedirectView("/member/login"); // 로그인 실패 시 다시 로그인 페이지로 이동한다.
    }

    //    로그인한 회원 정보 출력
    @GetMapping("myhome") // '/member/myhome'으로 요청 시 실행된다.
    public void goToMyHome(Model model) {
        Long id = ((MemberVO)session.getAttribute("memberVO")).getId(); // 세션에서 로그인한 회원의 ID를 꺼낸다.
        model.addAttribute("memberVO", memberService.selectId(id).orElseThrow(() -> { // 회원 정보를 조회
            throw new RuntimeException(); // ID로 회원 정보 조회 실패 시 예외 발생
        }));
    }

    //    회원 정보 수정 폼으로 이동
    @GetMapping("update") // '/member/update' 요청 시 실행된다.
    public void goToUpdate(Model model) {
       Long id = ((MemberVO)session.getAttribute("memberVO")).getId(); // 세션에서 ID 꺼낸다.
       MemberVO memberVO = memberService.selectId(id).orElseThrow(()->{
           throw new RuntimeException(); // DB에서 회원 정보 조회
       });
       model.addAttribute("id", id); // ID 전달
       model.addAttribute("memberVO", memberVO); // 회원 정보 전달 --> form에서 출력한다.
    }

//    회원 정보 수정 처리
    @PostMapping("update") //'/member/update'로 POST 요청 시 실행된다.
    public RedirectView update(MemberVO memberVO) {
       memberService.update(memberVO); // 전달 받은 정보로 DB 업데이트 실행한다.
       return new RedirectView("/member/myhome"); // 수정 후 마이페이지로 이동한다.
    }

//    로그 아웃
    @GetMapping("logout") // '/member/logout'으로 요청 시 실행된다.
    public RedirectView logout(){
       session.invalidate(); // 세션 정보 전체 삭제 --> 로그인 상태가 해제된다.
       return new RedirectView("/member/login");
    }

//    회원 탈퇴
    @GetMapping("delete") // '/member/delete'으로 요청 시 실행된다.
    public RedirectView delete() {
       Long id = ((MemberVO)session.getAttribute("memberVO")).getId(); // 세션에서 ID 꺼낸다.
        memberService.delete(id); // DB에서 해당 ID의 회원 삭제한다.
        return new RedirectView("/member/login"); // 로그인 페이지로 이동한다.
    }

}
