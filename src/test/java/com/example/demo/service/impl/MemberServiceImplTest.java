package com.example.demo.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.example.demo.entity.Member;
import com.example.demo.entity.Order;
import com.example.demo.entity.ProductCategory;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.ProductCategoryMapper;
import com.example.demo.mbextend.markentity.QOrder;
import com.example.demo.mbextend.markentity.QProduct;
import com.example.demo.mbextend.markentity.QProductCategory;
import com.example.demo.mbextend.utils.ExprUtil;
import com.example.demo.mbextend.SqlQuery;
import com.example.demo.mbextend.builder.SqlBuilder;
import com.example.demo.mbextend.markentity.QMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
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
    void testSimplyQuery1(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.username.eq("aaa"))
                .build();
        Member member = memberMapper.selectOne(sqlQuery);
        System.out.println(JSON.toJSONString(member));
    }

    @Test
    void testSimplyQuery2(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.username.in(Arrays.asList("aaa","bbb")))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        System.out.println(JSON.toJSONString(members));
    }

    @Test
    void testSimplyQuery3(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.birthday.between(
                        DateUtil.parse("2008-08-00"),
                        DateUtil.parse("2008-09-00")))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        members.forEach(m-> System.out.println(JSON.toJSONString(m)));
    }

    @Test
    void testSimplyQuery4(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.username.startWith("a"))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        System.out.println(JSON.toJSONString(members));
    }

    @Test
    void testLikeQuery(){
        QMember qMember = new QMember();
        QProduct qProduct = new QProduct();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .innerJoin(qProduct,qMember.username.endWith(qProduct.name))
                .select(qMember)
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        System.out.println(JSON.toJSONString(members));
    }

    @Test
    void testDistinct(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(qMember.gender)
                .distinct()
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        System.out.println(JSON.toJSONString(members));
    }

    @Test
    void testBinary(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.username.eq("AAA").binary())
                .build();
        Member member = memberMapper.selectOne(sqlQuery);
        System.out.println(JSON.toJSONString(member));
    }

    @Test
    void testNot(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.gender.eq("F").not())
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        System.out.println(JSON.toJSONString(members));
    }

    @Test
    void testCondition(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .where(qMember.gender.eq("F").or(qMember.gender.eq("M").or(qMember.birthday.eq(new Date()))),qMember.username.eq("aaa"))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        System.out.println(JSON.toJSONString(members));
    }

    @Test
    void testJoinTableQuery(){
        QMember qMember = new QMember();
        QOrder qOrder = new QOrder("ord_");
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .innerJoin(qOrder,qMember.id.eq(qOrder.memberId))
                .where(qMember.username.eq("aaa"))
                .where(ExprUtil.year(qOrder.createTimes).eq(2022))
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        System.out.println(JSON.toJSONString(members));
    }

    @Test
    void testSubQuery(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery1 = SqlBuilder.query(qMember)
                .select(qMember.id)
                .where(qMember.username.eq("aaa"))
                .build();

        QOrder qOrder = new QOrder();
        SqlQuery sqlQuery2 = SqlBuilder.query(qOrder)
                .select(qOrder.id, qOrder.createTimes)
                .where(qOrder.memberId.eq(sqlQuery1))
                .build();
        List<Order> orders = orderMapper.select(sqlQuery2);
        System.out.println(JSON.toJSONString(orders));
    }

    @Test
    void testSubQuery1(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery1 = SqlBuilder.query(qMember)
                .select(qMember.id)
                .where(qMember.gender.ge("F"))
                .build();

        QOrder qOrder = new QOrder();
        SqlQuery sqlQuery2 = SqlBuilder.query(qOrder)
                .select(qOrder.id, qOrder.createTimes)
                .where(qOrder.memberId.in(sqlQuery1))
                .build();
        List<Order> orders = orderMapper.select(sqlQuery2);
        System.out.println(JSON.toJSONString(orders));
    }

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
    void testExists(){
        QMember qMember = new QMember();

        SqlQuery query = SqlBuilder.query(qMember)
                .select(qMember.id)
                .where(qMember.username.eq("ccc"))
                .build();

        SqlQuery sqlQuery = SqlBuilder.query(null)
                .select(ExprUtil.ifExpr(ExprUtil.exists(query),true,false))
                .build();

        Boolean exists = memberMapper.exists(sqlQuery);
        System.out.println(exists);
    }

    @Test
    void testSwitchCaseFunc(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(ExprUtil.caseSwitch(qMember.gender,"F","女人","M","男人").column(qMember.gender),qMember.username)
                .build();
        List<Member> members = memberMapper.select(sqlQuery);
        System.out.println(JSON.toJSONString(members));
    }

    @Test
    void testCaseFunc(){
        QMember qMember = new QMember();
        SqlQuery sqlQuery = SqlBuilder.query(qMember)
                .select(ExprUtil.caseCondition(qMember.gender.eq("F"),"女人",qMember.gender.eq("M"),"男人").column(qMember.gender),qMember.username)
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

    @Test
    void testRecursiveETCQuery(){
        QProductCategory qpCategory = new QProductCategory();
        SqlQuery sqlQuery1 = SqlBuilder.query(qpCategory)
                .where(qpCategory.name.eq("服装")).build();
        sqlQuery1.setIsCte();

        SqlQuery sqlQuery2 = SqlBuilder.query(qpCategory)
                .innerJoin(sqlQuery1,qpCategory.parentId.eq(sqlQuery1.column(qpCategory.id)))
                .select(qpCategory)
                .build();

        SqlQuery sqlQuery3 = sqlQuery1.unionRecursiveCte(sqlQuery2);
        List<ProductCategory> categories = productCategoryMapper.select(SqlBuilder.query(sqlQuery3).build());
        System.out.println(JSON.toJSONString(categories));
    }
}