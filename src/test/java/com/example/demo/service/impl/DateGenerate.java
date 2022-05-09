package com.example.demo.service.impl;

import com.example.demo.entity.Member;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.mapper.*;
import com.example.demo.mbextend.SqlQuery;
import com.example.demo.mbextend.builder.QueryBuilder;
import com.example.demo.mbextend.builder.SqlBuilder;
import com.example.demo.mbextend.markentity.QMember;
import com.example.demo.mbextend.markentity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/5/9 17:03
 */
@SpringBootTest
public class DateGenerate {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductMapper productMapper;

    @Test
    void generateOrder(){
        QMember qMember = new QMember();
        List<Member> members = memberMapper.select(SqlBuilder.query(qMember).build());
        QProduct qProduct = new QProduct();
        List<Product> products = productMapper.select(SqlBuilder.query(qProduct).build());
        Random random = new Random();

        members.forEach(member -> {
            int orderCount = random.nextInt(10)+1;
            Date birthday = member.getBirthday();
            for (int i = 0; i < orderCount; i++) {
                Order order = new Order();
                order.setCreateTime(getRandDate(birthday));
                order.setMemberId(member.getId());
                int percent = random.nextInt(5);
                if(percent==4) {
                    order.setStatus(random.nextInt(9) + 1);
                }else{
                    order.setStatus(7);
                }

                int productCount = random.nextInt(5) + 1;
                List<Long> productId = new ArrayList<>(productCount);
                for (int j = 0; j < productCount; j++) {
                    OrderItem item = new OrderItem();
                    Product product;
                    while (true){
                        product = products.get(random.nextInt(products.size()));
                        if(!productId.contains(product.getId()) && product.getStock()>0 && product.getStatus()==1){
                            productId.add(product.getId());
                            break;
                        }
                    }
                    item.setProductId(product.getId());
                    item.setNumber();
                }
            }

        });
    }

    private OrderItem generateOrderItem(List<Product> products, Long orderId){
        Product product;
        Random random = new Random();
        while (true){
            product = products.get(random.nextInt(products.size()));
            if(!productId.contains(product.getId()) && product.getStock()>0 && product.getStatus()==1){
                productId.add(product.getId());
                break;
            }
        }
    }

    private Date getRandDate(Date birthday){
        int days = (int)(System.currentTimeMillis()-birthday.getTime())/1000/360/24;
        if(days<1){
            return null;
        }
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        calendar.add(Calendar.DATE,random.nextInt(days)+1);
        return calendar.getTime();
    }
}
