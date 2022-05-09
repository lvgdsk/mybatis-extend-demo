package com.example.demo.service.impl;

import cn.hutool.core.io.file.FileReader;
import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.mbextend.builder.SqlBuilder;
import com.example.demo.mbextend.markentity.QMember;
import com.example.demo.mbextend.markentity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    @Autowired
    ChinaRegionMapper chinaRegionMapper;

    @Test
    void generateChinaRegion() throws FileNotFoundException {
        FileReader fileReader = new FileReader("com/example/demo/service/impl/china_region.txt");
        String result = fileReader.readString();
        Map<String, String> regionMap = Arrays.stream(result.split("\n"))
                .collect(Collectors.toMap(
                item -> item.substring(0,6),
                item -> item.substring(6).trim())
        );
        Map<String, String> r1m = new HashMap<>();
        Map<String, String> r2m = new HashMap<>();
        Map<String, String> r3m = new HashMap<>();

        regionMap.forEach((k,v)->{
            if(k.matches("\\d\\d0000")){
                r1m.put(k,v);
            }else if(k.matches("\\d\\d\\d\\d00")){
                r2m.put(k,v);
            }else{
                r3m.put(k,v);
            }
        });

        System.out.println(r1m.size());
        System.out.println(r2m.size());
        System.out.println(r3m.size());

        Map<String, List<String>> r2g = r2m.keySet().stream().collect(Collectors.groupingBy(
                k -> k.substring(0, 2)));

        Map<String, List<String>> r3g = r3m.keySet().stream().collect(Collectors.groupingBy(
                k -> k.substring(0, 4)));

        r1m.forEach((k,v)->{
            ChinaRegion region = new ChinaRegion();
            region.setPid(0);
            region.setType(1);
            region.setRegion(v);
            chinaRegionMapper.insert(region);

            List<String> r2k = r2g.get(k.substring(0, 2));
            if(r2k!=null) {
                r2k.forEach(k1 -> {
                    ChinaRegion region1 = new ChinaRegion();
                    region1.setPid(region.getId());
                    region1.setType(2);
                    region1.setRegion(r2m.get(k1));
                    chinaRegionMapper.insert(region1);

                    List<String> r3k = r3g.get(k1.substring(0, 4));
                    if (r3k != null) {
                        r3k.forEach(k2 -> {
                            ChinaRegion region2 = new ChinaRegion();
                            region2.setPid(region1.getId());
                            region2.setType(3);
                            region2.setRegion(r3m.get(k2));
                            chinaRegionMapper.insert(region2);
                        });
                    }
                });
            }
        });
    }

    @Test
    void generateMember(){
        Random random = new Random();
        List<String> phoneStart = Arrays.asList("134","135","136","137","138","139","150","151","152","158","159","157","187","188","147","130","131","132","155","156","185","186","133","153","180","189");
//        List
        for (int i = 0; i < 1000; i++) {
            Member member = new Member();
            member.setUsername("user-"+i);
            member.setBirthday(generateBirthday(10));
            member.setGender(random.nextInt(2)==0?"M":"F");
            member.setPhone(phoneStart.get(random.nextInt(phoneStart.size()))+random.nextInt(90000000)+10000000);
            member.setMoney(new BigDecimal(random.nextInt(1000000)+10000));
//            member.setAddress();
        }
    }

    private Date generateBirthday(int maxYear){
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int year = nowYear-random.nextInt(100-maxYear);
        int month = random.nextInt(12);
        int day = random.nextInt(31);
        calendar.set(year,month,day);
        return calendar.getTime();
    }

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
//                    item.setNumber();
                }
            }

        });
    }

//    private OrderItem generateOrderItem(List<Product> products, Long orderId){
//        Product product;
//        Random random = new Random();
//        while (true){
//            product = products.get(random.nextInt(products.size()));
//            if(!productId.contains(product.getId()) && product.getStock()>0 && product.getStatus()==1){
//                productId.add(product.getId());
//                break;
//            }
//        }
//    }

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
