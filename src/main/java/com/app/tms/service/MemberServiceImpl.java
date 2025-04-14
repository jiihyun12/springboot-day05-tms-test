package com.app.tms.service;

import com.app.tms.domain.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.app.tms.repository.MemberDAO;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;

    @Override
    public void insertMember(MemberVO memberVO) {
        memberDAO.insert(memberVO);
    }

    public MemberDAO getMemberDAO() {
        return memberDAO;
    }
}



