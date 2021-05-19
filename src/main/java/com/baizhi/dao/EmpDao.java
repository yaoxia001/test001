package com.baizhi.dao;

import com.baizhi.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 姚天下
 */
@Mapper //用来创建Dao对象
public interface EmpDao {

   List<Emp> findAll();

   void save(Emp emp);

   void  delete(String id);

   Emp findOneById(String id);


}
