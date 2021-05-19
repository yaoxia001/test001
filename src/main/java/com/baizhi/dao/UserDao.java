package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 姚天下
 */
@Mapper //用来创建Dao对象
public interface UserDao {

    void save(User user);

    User findByUserName(String userName);
}
