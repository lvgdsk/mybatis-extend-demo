package com.example.demo.controller;


import com.example.demo.entity.Member;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.mbextend.QMember;
import com.example.demo.mbextend.builder.SqlBuilder;
import com.example.demo.mbextend.sqlparts.SqlQuery;
import com.example.demo.service.IMemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lvqi
 * @since 2022-05-05
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    private final IMemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(IMemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping("/addMember")
    private String addMember(@RequestBody Member member){
        memberService.save(member);
        return "success";
    }

    @GetMapping("/getMemberByUsername")
    private List<Member> getMemberByUsername(String username){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember).where(qMember.username.eq(username)).build();
        return memberMapper.select(sqlQuery);
    }
}

