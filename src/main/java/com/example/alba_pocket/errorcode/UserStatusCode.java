package com.example.alba_pocket.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserStatusCode implements StatusCode{

    NOT_FOUND_AUTHORIZATION_IN_SECURITY_CONTEXT("Security Context에 인증 정보가 없습니다.", HttpStatus.BAD_REQUEST.value()),
    USER_SIGNUP_SUCCESS("회원가입 성공", HttpStatus.OK.value()),

    USER_LOGIN_SUCCESS("로그인 성공", HttpStatus.OK.value()),

    WRONG_USERID_PATTERN("USERID는 이메일 형식으로 작성되어야 합니다.", HttpStatus.BAD_REQUEST.value()),
    WRONG_NICKNAME_PATTERN("닉네임은 최소 5자 이상, 10자 이하이며 알파벳 대소문자(A-Za~z), 숫자(0~9)로 구성되어야 합니다.", HttpStatus.BAD_REQUEST.value()),
    WRONG_PASSWORD_PATTERN("비밀번호는 최소 8자 이상, 20자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자가 포함되어야 합니다.", HttpStatus.BAD_REQUEST.value()),
    WRONG_PASSWORD("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST.value()),
    NO_USER("회원을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST.value()),
    DELETE_USER("탈퇴한 사용자 입니다.", HttpStatus.BAD_REQUEST.value()),
    OVERLAPPED_USERNAME("중복된 username 입니다.", HttpStatus.BAD_REQUEST.value()),
    OVERLAPPED_NICKNAME("이미 존재하는 닉네임입니다.", HttpStatus.BAD_REQUEST.value()),
    PASSWORD_CHECK("입력된 비밀번호가 다릅니다.", HttpStatus.BAD_REQUEST.value()),
    INVALID_TOKEN("토큰이 유효하지 않습니다.", HttpStatus.BAD_REQUEST.value());

    private final String Msg;
    private final int statusCode;
}
