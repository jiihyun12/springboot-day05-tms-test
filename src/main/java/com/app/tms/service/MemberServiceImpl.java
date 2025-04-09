package com.app.tms.service;

import com.app.tms.domain.MemberVO;
import com.app.tms.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service // 이 클래스가 서비스 계층 클래스임을 Spring에게 알려주는 어노테이션
        // 해당 클래스는 스프링 컨테이너에 빈으로 등록된다.
        // 주로 컨트롤러에서 전달 받은 요청을 처리하거나, DAO를 호출해 DB 작업을 수행하는 핵심 로직을 담는다.
@RequiredArgsConstructor // 생성자 자동 생성
                        // @AutoWired없이도 스프링이 알아서 Bean을 주입해준다.
                        // final 필드를 반드시 초기화하게 강제하므로 코드 안정성도 높아진다.
@Transactional(rollbackFor = Exception.class) // 트랜잭션 관리
                                              // 메서드 실행준 예외가 발생하면 DB 작업을 자동으로 rollback한다.
                                              // 회원가입, 수정, 탈퇴 같은 작업에서 데이터 무결성을 지키기 위해 필요하다.

public class MemberServiceImpl implements MemberService {
// Implements
// 서비스 인터페이스의 구현체
// 서비스에서는 뭘 해야하는지 정의만 하고 임플리먼츠 클래스에서 실제로 구현한다.

    private final MemberDAO memberDAO;

//    회원 가입
    @Override
    public void join(MemberVO memberVO) {
        memberDAO.save(memberVO);
    }

//    로그인
    @Override
    public Optional<MemberVO> select(MemberVO memberVO) {
        return memberDAO.select(memberVO);
    }

//    단일 회원 조회
    @Override
    public Optional<MemberVO> selectId(Long id) {
        return memberDAO.selectId(id);
    }

//    회원 수정
    @Override
    public void update(MemberVO memberVO) {
        memberDAO.update(memberVO);
    }

//    회원 탈퇴
    @Override
    public void delete(Long id) {
        memberDAO.delete(id);
    }
}
