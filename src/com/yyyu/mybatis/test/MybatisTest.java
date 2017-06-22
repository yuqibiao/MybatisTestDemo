package com.yyyu.mybatis.test;

import com.yyyu.mybatis.dao.UserDao;
import com.yyyu.mybatis.dao.inter.IUserDao;
import com.yyyu.mybatis.mapper_dao.UserMapper;
import com.yyyu.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 功能：单元测试
 *
 * @author yu
 * @date 2017/6/22.
 */


public class MybatisTest {


    private SqlSessionFactory sessionFactory;

    @Before
    public void init(){
        //1.创建SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream inputStream = null;
        try {
            //2.加载SqlMapConfig.xml配置文件
            inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            //加载log4j配置
            PropertyConfigurator.configure(Resources.getResourceAsProperties("loj4j.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取配置文件异常==="+e.getMessage());
        }
        //3.创建SqlSessionFactory
        sessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    @Test
    public void testQueryUserById(){
        //4.创建SqlSession
        SqlSession session = sessionFactory.openSession();
        //5.执行SqlSession
        User user = session.selectOne("queryByUserId" , 1);
        System.out.println(user);
        session.close();
    }

    @Test
    public void testQueryUserByName(){
        //4.创建SqlSession
        SqlSession session = sessionFactory.openSession();
        //5.执行SqlSession
        List<User> userList= session.selectList("queryByUsername" ,"%张%");
        for (User user:userList) {
            System.out.println(user);
        }
        session.close();
    }

    @Test
    public void testQueryUserByName2(){
        //4.创建SqlSession
        SqlSession session = sessionFactory.openSession();
        //5.执行SqlSession
        List<User> userList= session.selectList("queryByUsername2" ,"张");
        for (User user:userList) {
            System.out.println(user);
        }
        session.close();
    }

    @Test
    public void testSaveUser() throws ParseException {
        SqlSession session = sessionFactory.openSession();
        User user = new User();
        user.setUsername("yu");
        user.setSex("0");
        Date birthday = new SimpleDateFormat("yyy-MM-dd").parse("1992-06-05");
        user.setBirthday(birthday);
        user.setAddress("湖北武汉");
        session.insert("saveUser" , user);
        //---一定要提交事务（查询得时候不需要）
        session.commit();
        session.close();
    }

    @Test
    public void testUpdateByUserId() throws ParseException {
        SqlSession session = sessionFactory.openSession();
        User user = new User();
        user.setId(7);
        Date birthday = new SimpleDateFormat("yyy-MM-dd").parse("1992-06-05");
        user.setBirthday(birthday);
        session.update("updateByUserId" , user);
        session.commit();
        session.close();
    }

    @Test
    public void testDeleteByUserId(){
        SqlSession session = sessionFactory.openSession();
        session.delete("deleteByUserId" , 8);
        session.commit();
        session.close();
    }


    /**
     * 传统方式DAO开发
     */
    @Test
    public  void  testDao(){
        IUserDao userDao = new UserDao(sessionFactory);
        User user = userDao.getByUserId(1);
        System.out.println(user);
    }

    /**
     * 使用动态代理得DAO开发
     *
     */
    @Test
    public void testMapperDao(){
/*
        SqlSession session = sessionFactory.openSession();
         UserMapper userMapper = session.getMapper(UserMapper.class);
         User user = userMapper.queryByUserId(1);
        System.out.println(user);*/

        SqlSession session = sessionFactory.openSession();
         UserMapper userMapper = session.getMapper(UserMapper.class);
         List<User>  userList= userMapper.queryByUsername("%张%");
        for (User user:userList) {
            System.out.println(user);
        }
    }


}
