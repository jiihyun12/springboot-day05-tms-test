package com.app.tms.mapper;

import com.app.tms.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

//    회원 가입
    public void insert(MemberVO memberVO);
}
