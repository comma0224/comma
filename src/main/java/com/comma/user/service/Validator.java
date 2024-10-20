package com.comma.user.service;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern USERID_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])[a-zA-Z0-9]{6,}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$");
    private static final Pattern NICKNAME_PATTERN = Pattern.compile("^(?=.*[가-힣]{2,}|[a-zA-Z]{4,})[a-zA-Z가-힣0-9]+$");

    public static void isValidateLoginUserId(String userId, UserService service) throws Exception {
        if (userId == null || userId.isEmpty()) {
            throw new Exception("아이디를 입력해 주세요.");
        }
        if (!USERID_PATTERN.matcher(userId).matches()) {
            throw new Exception("아이디는 6자리 이상 영문과 숫자만 입력 가능합니다.");
        }
        if (!service.isUserIdDuplicate(userId)) {
            throw new Exception("존재하지 않는 아이디입니다.");
        }
    }

    public static void isValidateLoginPassword(String password) throws Exception {
        if (password == null || password.isEmpty()) {
            throw new Exception("비밀번호를 입력해 주세요.");
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new Exception("비밀번호는 8자리 이상 영문, 숫자, 특수문자를 포함해야 합니다.");
        }
    }

    public static void isValidateSignupUserId(String userId, UserService service) throws Exception {
        if (userId == null || userId.isEmpty()) {
            throw new Exception("아이디를 입력해 주세요.");
        }
        if (!USERID_PATTERN.matcher(userId).matches()) {
            throw new Exception("아이디는 6자리 이상 영문과 숫자만 입력 가능합니다.");
        }

        if (service.isUserIdDuplicate(userId)) {
            throw new Exception("사용 중인 아이디 입니다.");
        }
    }

    public static void isValidateSignupPassword(String password) throws Exception {
        if (password == null || password.isEmpty()) {
            throw new Exception("비밀번호를 입력해 주세요.");
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new Exception("비밀번호는 8자리 이상 영문, 숫자, 특수문자를 포함해야 합니다.");
        }
    }

    public static void isValidateSignupNickname(String nickname, UserService service) throws Exception {
        if (nickname == null || nickname.isEmpty()) {
            throw new Exception("별명을 입력해 주세요.");
        }

        if (!NICKNAME_PATTERN.matcher(nickname).matches()) {
            throw new Exception("별명은 한글 2자 이상, 영문 4자 이상이어야 합니다.");
        }

        if (service.isNicknameDuplicate(nickname)) {
            throw new Exception("사용 중인 아이디 입니다.");
        }
    }




}
