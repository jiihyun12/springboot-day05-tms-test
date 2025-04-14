package com.app.tms.repository;

import com.app.tms.domain.MemberVO;
import lombok.RequiredArgsConstructor;
import com.app.tms.mapper.MemberMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    private final MemberMapper memberMapper;

//    회원가입
    public void insert(MemberVO memberVO) {memberMapper.insert(memberVO);}

}
