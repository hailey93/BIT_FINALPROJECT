package com.bit.house.domain;

public enum SocialType {
    //FACEBOOK("facebook"),
    NULL("null"),
    GOOGLE("google"),
    KAKAO("kakao"),
    NAVER("naver");

    private final String ROLE_PREFIX = "ROLE_";
    private String name;

    SocialType(String name) {
        this.name = name;
    }

    public String getRoleType() {
        return ROLE_PREFIX + name.toUpperCase();
    }

    public String getValue() {
        return name;
    }

    public boolean isEquals(String authority) {

        return this.name.equals(authority);
    }

}