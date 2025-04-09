package com.app.tms.domain; // 이 클래스가 위치한 폴더(패키지)를 나타낸다.

import lombok.Data; // lombok이라는 도구의 Data 어노테이션 사용을 위해 import
                    // lombok은 반복되는 코드를 줄여주는 도구이다.(예를 들면 getter/setter 자동 생성)

import org.springframework.stereotype.Component; // spring에서 이 클래스를 관리할 수 있도록 만드는 어노테이션을 import

@Component // Spring이 이 클래스를 자동으로 메모리에 올려서 사용할 수 있게 해준다.
           // 다른 클래스에서 @Autowired로 주입 받아 가져다 쓸 수 있다.
@Data // 기본 생성자, getter, setter, toString, equals, hashcode 메서드 자동 생성
public class MemberVO { // 회원 정보를 담는 클래스 (VO는 Value Object이다.)
   private Long id;
   private String memberNameEmail;
   private String memberPassword;
   private String memberName;
   private String memberAddress;
   private String memberPhone;
}
