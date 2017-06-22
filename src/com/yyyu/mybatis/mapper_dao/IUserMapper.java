package com.yyyu.mybatis.mapper_dao;

import com.yyyu.mybatis.pojo.bean.User;

import java.util.List;

/**
 * 功能：user表操作对应得Mapper接口
 *
 * @author yu
 * @date 2017/6/22.
 */
public interface IUserMapper {

    /*这里得名字要和User.xml文件中得id名一致*/
    User queryByUserId(int id);

    List<User> queryByUsername(String username);

}
