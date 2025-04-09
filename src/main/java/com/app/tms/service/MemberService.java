package com.app.tms.service;


import com.app.tms.domain.MemberVO;

import java.util.Optional;

public interface MemberService {

//    회원 가입
//    컨트롤러에서 전달 받은 memberVO를 DB에 저장한다.
//    회원 가입 버튼을 누르면 -> 컨트롤러로 갔다가 -> Service.join(memberVO) -> DAO.save의 흐름이다.
    public void join(MemberVO memberVO);

//    회원 조회
//    로그인을 하면 이메일과 비밀번호가 담긴 memberVO 객체를 받아서 DB에서 일치하는 회원을 조회한다.
//    로그인 요청 -> Controller -> Service.select(memberVO) -> DAO.select(memberVO) -> Optional<MemberVO> (리턴값) 의 흐름이다.
    public Optional<MemberVO> select(MemberVO memberVO);

//    회원 단일 조회
//    특정 회원의 ID값으로 회원 정보를 조회한다.
//    session에서 꺼낸 id로 조회한다.
    public Optional<MemberVO> selectId(Long id);

//    회원 수정
//    회원 이름, 주소, 전화번호 등 변경할 정보를 담은 memberVO객체를 받아서 DB에 반영한다.
    public void update(MemberVO memberVO);

//    회원 탈퇴
//    특정 회원의 ID를 기준으로 DB에서 해당 회원을 삭제한다.
    public void delete(Long id);
}
