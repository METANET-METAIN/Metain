package com.metain.web.mapper;

import com.metain.web.domain.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface MemberMapper {

    /**로그인*/
//    public Emp login(Emp emp);

    /**로그아웃*/
    public Emp logout(Emp emp);

    //로그인
    public Emp login(@Param("empSabun") String empSabun);




}
