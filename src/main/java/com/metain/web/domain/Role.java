package com.metain.web.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {

    SW("EMPLOYEE"), //사원
    DR("ASSISTANT"), //대리
    GJ("MANAGER"), //과장
    CJ("DEPUTY"), //차장
    HR("HR"), //인사관리
    ADMIN("ADMIN"), //관리자
    ACT("ACTIVE"), //재직자
    RET("RETIREE"); //퇴직자

    private String role;

    private String roleName;



    Role(String role) {
        this.role = role;
    }




    public String value() {
        return role;
    }

    @Override
    public String getAuthority(){
        return role;
    }

    // 등급과 역할 매핑
    public static Role fromGrade(String grade) {
        switch (grade) {
            case "ADMIN":
                return ADMIN;
            case "EMPLOYEE":
                return SW;
            case "ASSISTANT":
                return DR;
            case "MANAGER":
                return GJ;
            case "DEPUTY":
                return CJ;
            case "HR":
                return HR;
            default:
                throw new IllegalArgumentException("Invalid grade: " + grade);
        }
    }

    // 상태와 역할 매핑
    public static Role fromStatus(String status) {
        switch (status) {
            case "ACTIVE":
                return ACT;
            case "RETIREE":
                return RET;
            default:
                throw new IllegalArgumentException("Invalid status: " + status);
        }
    }

}