package com.app.tms.mapper;

import com.app.tms.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper // 매퍼임을 스프링에게 알려준다. mapper xml파일과 자동 연결된다.
public interface MemberMapper {
    //  param으로 MemberVO 객체를 전달받아 DB에 회원 데이터를 넣는다.
    public void insert(MemberVO memberVO);

    // 회원 정보 조회(로그인)
    // 이메일과 비밀번호가 모두 일치하는 회원을 조회한다.
    // param으로 이메일과 비밀번호가 담긴 memberVO객체를 전달받는다.
    // return타입은 memberVO이다.(조회된 회원 정보)
    // Optional을 붙이는 이유는 null일수도 있는 값을 안전하게 다루기 위해서이다. -> Optional<T>
    public Optional<MemberVO> select(MemberVO memberVO);

    // 마이페이지로 갈 때 로그인된 회원의 ID로 정보를 불러온다.
    // session에 저장된 회원의 ID를 기준으로 회원 정보를 가져온다.
    public Optional<MemberVO> selectId(Long id);

//    회원 정보 수정
    // 비밀번호, 이름, 주소, 전화번호 등을 변경한다.
    // ID를 기준으로 업데이트 된다. (mapper xml의 update id와 연결됨)
    // param으로 수정된 회원 정보의 객체를 받는다.
    public void update(MemberVO memberVO);

//    회원 탈퇴
    // ID를 기준으로 DB에서 회원 정보를 삭제한다.
    public void delete(Long id);
}
