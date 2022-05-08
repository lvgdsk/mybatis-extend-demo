package com.example.demo.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.demo.entity.Member;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.ProductCategoryMapper;
import com.example.demo.mbextend.utils.ExprUtil;
import com.example.demo.mbextend.SqlQuery;
import com.example.demo.mbextend.builder.SqlBuilder;
import com.example.demo.mbextend.markentity.QMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/5/6 14:46
 */
@SpringBootTest
class MemberServiceImplTest {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    void testSimplyQuery(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.username.eq("小月"))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        System.out.println(JSON.toJSONString(members));
    }

//    @Test
//    void testJoinTableQuery(){
//        QMember qMember = new QMember();
//        QOrder qOrder = new QOrder("ord_");
//        SqlQuery sqlQuery = SqlBuilder.query(qMember)
//                .innerJoin(qOrder,qMember.id.eq(qOrder.memberId))
//                .where(qMember.username.eq("aaa"))
//                .where(ExprUtil.year(qOrder.createTime).eq(2022))
//                .build();
//        List<Member> members = memberMapper.select(sqlQuery);
//        System.out.println(JSON.toJSONString(members));
//    }

//    @Test
//    void testSubQuery(){
//        QMember qMember = new QMember();
//        SqlQuery sqlQuery1 = SqlBuilder.query(qMember)
//                .select(qMember.id)
//                .where(qMember.username.eq("aaa"))
//                .build();
//
//        QOrder qOrder = new QOrder();
//        SqlQuery sqlQuery2 = SqlBuilder.query(qOrder)
//                .select(qOrder.id, qOrder.createTime)
//                .where(qOrder.memberId.eq(sqlQuery1))
//                .build();
//        List<Order> orders = orderMapper.select(sqlQuery2);
//        System.out.println(JSON.toJSONString(orders));
//    }

    @Test
    void testUnionQuery(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery1 = SqlBuilder.query(qMember)
                .where(qMember.username.eq("aaa"))
                .build();

        SqlQuery sqlQuery2 = SqlBuilder.query(qMember)
                .where(qMember.username.eq("bbb"))
                .build();


        List<Member> members = memberMapper.select(sqlQuery1.union(sqlQuery2));
        System.out.println(JSON.toJSONString(members));
    }


    @Test
    void testIfFunc(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(ExprUtil.ifExpr(qMember.gender.eq("F"),"女人","男人").column(qMember.gender),qMember.username)
                .where(qMember.username.eq("aaa"))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        System.out.println(JSON.toJSONString(members));
    }

    @Test
    void testCaseFunc(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(ExprUtil.caseSwitch(qMember.gender,"F","女人","M","男人").column(qMember.gender),qMember.username)
                .where(qMember.username.eq("aaa"))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        System.out.println(JSON.toJSONString(members));
    }

    @Test
    void testConcatFunc(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(ExprUtil.concat(qMember.username,"123123").column(qMember.username),qMember.username)
                .where(qMember.username.eq("aaa"))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        System.out.println(JSON.toJSONString(members));
    }

    @Test
    void testETCQuery(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.username.eq("aaa"))
                .build();
        sqlQuery.setIsCte();
        SqlQuery etcQuery = SqlBuilder.query(sqlQuery).build();
        List<Member> members = memberMapper.select(etcQuery);
        System.out.println(JSON.toJSONString(members));
    }

//    @Test
//    void testRecursiveETCQuery(){
//        QProductCategory qpCategory = new QProductCategory();
//        SqlQuery sqlQuery1 = SqlBuilder.query(qpCategory)
//                .where(qpCategory.name.eq("服装")).build();
//        sqlQuery1.setIsCte();
//
//        SqlQuery sqlQuery2 = SqlBuilder.query(qpCategory)
//                .innerJoin(sqlQuery1,qpCategory.parentId.eq(sqlQuery1.column(qpCategory.id)))
//                .select(qpCategory)
//                .build();
//
//        SqlQuery sqlQuery3 = sqlQuery1.unionRecursiveCte(sqlQuery2);
//        List<ProductCategory> categories = productCategoryMapper.select(SqlBuilder.query(sqlQuery3).build());
//        System.out.println(JSON.toJSONString(categories));
//    }
}