package com.yyyu.mybatis.dao.inter;

import com.yyyu.mybatis.pojo.bean.User;

/**
 * 功能：用户表对应得dao操作接口
 *
 * @author yu
 * @date 2017/6/22.
 */
public interface IUserDao {

    User getByUserId(int id);

}
