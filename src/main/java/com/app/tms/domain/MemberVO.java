package com.app.tms.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MemberVO {
    Long id;
    String memberEmail;
    String memberPassword;
    String memberName;
    String memberAddress;
    String memberPhone;
}
