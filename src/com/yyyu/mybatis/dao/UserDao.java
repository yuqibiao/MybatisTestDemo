package com.yyyu.mybatis.dao;

import com.yyyu.mybatis.dao.inter.IUserDao;
import com.yyyu.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 功能：用户表对应得dao操作
 *
 * 原始Dao开发
 *
 * @author yu
 * @date 2017/6/22.
 */
public class UserDao implements IUserDao{

    private SqlSessionFactory sqlSessionFactory;

    public UserDao(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }


    @Override
    public User getByUserId(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        User user = session.selectOne("queryByUserId" , 1);
        session.commit();
        session.close();
        return user;
    }

}
