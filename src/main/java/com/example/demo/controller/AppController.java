package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Pet;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mbextend.QEmployee;
import com.example.demo.mbextend.QField;
import com.example.demo.mbextend.QPet;
import com.example.demo.mbextend.QUser;
import com.example.demo.mbextend.builder.SqlBuilder;
import com.example.demo.mbextend.enums.TimeField;
import com.example.demo.mbextend.sqlparts.*;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.PetService;
import com.example.demo.service.UserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/20 9:43
 */
@RestController
public class AppController {

    private final UserService userService;
    private final PetService petService;
    private final EmployeeService employeeService;
    private final UserMapper userMapper;
    private final SqlSessionFactory factory;

    @Autowired
    private DataSource dataSource;

    public AppController(UserService userService,
                         PetService petService,
                         EmployeeService employeeService,
                         UserMapper userMapper,
                         SqlSessionFactory factory) {
        this.userService = userService;
        this.petService = petService;
        this.employeeService = employeeService;
        this.userMapper = userMapper;
        this.factory = factory;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        boolean save = userService.save(user);
        System.out.println(user);
        return user;
    }

    @PostMapping("/addPet")
    public Pet addPet(@RequestBody Pet pet){
        boolean save = petService.save(pet);
        System.out.println(save);
        return pet;
    }

    @GetMapping("/listAllUser")
    public List<User> listAllUser(){
        return userService.list();
    }

    @GetMapping("/test")
    private List<Pet> test(){
        QPet pet = new QPet("p","pet");

        SqlExpr rank = SqlExpr.overRank(pet.uid, Collections.singletonList(pet.species), Collections.singletonList(pet.uid.orderAsc()));
        SqlQuery sqlQuery = SqlBuilder.query(pet)
                .select(pet.name)
                .select(rank).build();

        SqlField rankField = sqlQuery.column(rank);

        SqlQuery sqlQuery1 = SqlBuilder.query(sqlQuery)
                .select(sqlQuery.column(pet.name), rankField)
                .where(rankField.le(1)).build();

        return petService.select(sqlQuery1);
    }

    @GetMapping("/select")
    private List<User> select(){
        QUser user = new QUser("u","user");
        QPet pet = new QPet("p","pet");
        SqlQuery sqlQuery = SqlBuilder.query(user)
                .leftjoin(pet, pet.uid.eq(user.id))
                .select(user)
                .select(pet.name, pet.species).build();

        return userService.select(sqlQuery);
    }

    @GetMapping("/select1")
    private List<User> select1(){

        QUser user = new QUser("u","user");
        QPet pet = new QPet("p","pet");
        SqlQuery sqlQuery = SqlBuilder
                .query(user).leftjoin(pet,pet.uid.eq(user.id))
                .select(user)
//                .select(pet.id,SqlExpr.caseCondition(pet.name,pet.id.eq(2),"aa",pet.id.eq(3),"bb","cc"))
                .select(pet.id,SqlExpr.caseSwitch(pet.name,pet.id,2,"aa",3,"bb","cc"))
                .where(SqlExpr.dateAdd(user.birthday,2, TimeField.DAY).eq(getDate("2022-02-18 14:00:00"))).build();
        return userService.select(sqlQuery);
    }

    @GetMapping("/select2")
    private List<User> select2(){
        QUser user = new QUser("u","user");
        QPet pet = new QPet("p","pet");
        SqlQuery sqlQuery1 = SqlBuilder.query(pet)
                .select(pet.uid)
                .where(pet.id.eq(2)).build();
        SqlQuery sqlQuery2 = SqlBuilder.query(user)
                .select(user)
                .where(user.id.eq(sqlQuery1)).build();
        return userService.select(sqlQuery2);
    }

    @GetMapping("/update1")
    private Integer update1(){
        QUser user = new QUser("u");
        QPet pet = new QPet("p");
        SqlQuery sqlQuery = SqlBuilder.query(pet)
                .select(pet.uid)
                .where(pet.id.eq(2)).build();
        SqlUpdate sqlUpdate = SqlBuilder.update(user)
                .set(user.name,"kkgdsk")
                .where(user.id.eq(sqlQuery)).build();
        return userService.update(sqlUpdate);
    }

    @GetMapping("/update2")
    private Integer update2(){
        QUser user = new QUser("u",null);
        QPet pet = new QPet("p",null);
        SqlUpdate sqlUpdate = SqlBuilder.update(user)
                .join(pet, pet.uid.eq(user.id))
                .set(user.birthday, new java.util.Date())
                .set(pet.species,"鸟")
                .where(user.id.eq(52)).build();
        return userService.update(sqlUpdate);
    }

    @GetMapping("/delete1")
    private Integer delete1() {
        QUser user = new QUser("u");
        QPet pet = new QPet("p");
        SqlQuery sqlQuery = SqlBuilder.query(pet)
                .select(pet.uid)
                .where(pet.id.eq(3)).build();

        SqlDelete sqlDelete = SqlBuilder.delete(user)
                .where(user.id.eq(sqlQuery)).build();

        return userService.delete(sqlDelete);
    }

    @GetMapping("/testUnion")
    public List<Pet> testUnion(){
        QPet pet = new QPet("p","pet");
        SqlQuery sqlQuery1 = SqlBuilder.query(pet)
                .select(pet)
                .where(pet.id.eq(3)).build();

        SqlQuery sqlQuery2 = SqlBuilder.query(pet)
                .select(pet)
                .where(pet.id.eq(2)).union(sqlQuery1).build();
        return petService.select(sqlQuery2);
    }

    @GetMapping("/testCte")
    public List<Employee> testCte(){

        QEmployee qe = new QEmployee("emp",null);

        SqlQuery sqlQuery = SqlBuilder
                .query(qe)
                .select(qe)
                .select(QField.column("1","lvl"))
                .where(qe.leaderNumber.isNull())
                .columnPrefix("employee")
                .tableAlias("cte")
                .build("emp_path");

        SqlQuery sqlQuery1 = SqlBuilder
                .query(qe)
                .join(sqlQuery, qe.leaderNumber.eq(sqlQuery.column(qe.empNumber)))
                .select(qe)
                .select(QField.column("lvl+1","lvl"))
                .build();

        sqlQuery.unionRecursive(sqlQuery1);
        SqlQuery sqlQuery2 = SqlBuilder.query(sqlQuery).build();

        return employeeService.select(sqlQuery2);
    }

    private Date getDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

//    @GetMapping("test1")
//    public List<User> test1() throws SQLException {
////        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
////        queryWrapper.select("*");
////        queryWrapper.eq("name","aaa");
////        queryWrapper.eq("birthday","2022-03-03");
////        return userService.list(queryWrapper);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR,2022);
//        calendar.set(Calendar.MONTH,1);
//        calendar.set(Calendar.DATE,16);
//        calendar.set(Calendar.HOUR_OF_DAY,14);
//        calendar.set(Calendar.MINUTE,0);
//        calendar.set(Calendar.SECOND,0);
//        calendar.set(Calendar.MILLISECOND,0);
//
////        Connection connection = dataSource.getConnection();
////        long start = System.currentTimeMillis();
////        PreparedStatement preparedStatement = connection.prepareStatement(" ? ");
////        preparedStatement.setTimestamp(1,new Timestamp(calendar.getTimeInMillis()));
////        System.out.println(preparedStatement)
//
//
////        long end = System.currentTimeMillis();
////        System.out.println(end-start);
//
////        return userMapper.fildUser(calendar.getTime());
//        SqlQuery sqlQuery = new SqlQuery("select id user_id,name user_name,birthday user_birthday from user where name = #{sqlQuery.params.name}");
//        Map<String,Object> params = new HashMap<>(1);
//        params.put("name","kkgdsk");
//        sqlQuery.setParams(params);
//        return userMapper.select(sqlQuery);
//    }

}
