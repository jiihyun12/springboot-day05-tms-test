package com.app.tms.service;

import com.app.tms.domain.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j // 로그를 찍기 위한 어노테이션 ( log.info() 를 가능하게 한다.)
@SpringBootTest // 스프링부트 통합 테스트를 가능하게 해주는 어노테이션
public class MemberServiceTests { // MemberService의 기능을 검증하는 단위테스트 클래스
                                  // 실제 DB에 반영된다.

    @Autowired // 테스트 대상 서비스 주입
    MemberService memberService;

    // 회원 가입 기능 테스트
    @Test // 테스트용 메서드임을 알려준다.
          // 테스트 프레임워크인 JUnit이 이 어노테이션을 보고 자동으로 실행해준다.
    public void insertTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberNameEmail("test123@gmail.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("홍길동");
        memberVO.setMemberAddress("서울시 어딘가");
        memberVO.setMemberPhone("01012345678");
        memberService.join(memberVO);
    }

    // 로그인 테스트
    // 입력한 이메일과 비밀번호가 DB에 존재하는지 확인한다.
    @Test
    public void selectTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberNameEmail("test2000020000@gmail.com");
        memberVO.setMemberPassword("test123");
        Optional<MemberVO> foundMember = memberService.select(memberVO);

        log.info("------------------------------");
        if(foundMember.isPresent()) {
            log.info( "로그인 성공하였습니다: {}" , foundMember.get());
        }else {
            log.info("로그인에 실패하였습니다.");
        }
    }

    // 회원 정보 수정 테스트
    // ID를 기준으로 회원의 이름과 비밀번호를 수정하여 확인한다.
    @Test
    public void updateTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setId(1L);
        memberVO.setMemberPassword("7890");
        memberVO.setMemberName("홍홍길");

        memberService.update(memberVO);

        log.info("------------------------------");
        log.info("회원 정보가 수정되었습니다. {}" , memberVO);
    }

//    회원 탈퇴 테스트
    @Test
    public void deleteTest() {
        MemberVO memberVO = new MemberVO();
        memberService.delete(1L); // ID가 1인 회원을 DB에서 삭제한다.
    }
}
