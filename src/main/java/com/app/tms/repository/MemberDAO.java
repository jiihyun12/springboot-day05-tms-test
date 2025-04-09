package com.app.tms.repository;

import com.app.tms.domain.MemberVO;
import com.app.tms.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 이 클래스가 DAO임을 스프링에게 알려주는 어노테이션
           // 스프링이 이 객체를 자동으로 생성해서 관리하게 해준다.
           // @Component의 하위 개념으로, DAO전용 표현이다.
@RequiredArgsConstructor // 생성자 자동 생성
                        // 별도로 @Autowired를 사용하지 않아도 되게 해준다.
                        // 의존성 주입을 위해 생성자가 필요한데, DI할 객체를 자동으로 만들어준다.
public class MemberDAO { // 이 클래스는 DB와 직접 연결된 MemberMapper를 의존성 주입 받아서
                        // 서비스 계층에서 요청한 DB 작업을 실행한다.
                       // SQL은 Mapper에게 넘기고, DAO는 중간에서 호출하는 역할을 한다.

    private final MemberMapper memberMapper;

//    회원 가입
//    insert sql 실행
//    회원 정보를 Mapper에 넘겨서 DB에 삽입한다.
    public void save(MemberVO memberVO) {memberMapper.insert(memberVO);}

//    회원 조회
//    이메일과 비밀번호로 회원 한 명을 조회한다.
//    로그인 시 아이디/비밀번호가 맞는 회원을 찾아준다.
    public Optional<MemberVO> select(MemberVO memberVO) { return memberMapper.select(memberVO);}

//    회원 단일 조회
//    회원 ID를 기준으로 회원 정보를 조회한다.
    public Optional<MemberVO> selectId(Long id){return memberMapper.selectId(id);}

//    회원 수정
//    회원 비밀번호, 이름, 주소, 전화번호 등을 수정한다.
//    ID를 기준으로 업데이트한다.
    public void update(MemberVO memberVO) {memberMapper.update(memberVO);}

//    회원 삭제
//    회원 ID로 정보를 삭제한다.
    public void delete(Long id){memberMapper.delete(id);}
}

