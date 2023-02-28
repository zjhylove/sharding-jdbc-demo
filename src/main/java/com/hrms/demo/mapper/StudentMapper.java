package com.hrms.demo.mapper;

import com.hrms.demo.po.StudentPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Student持久化服务
 *
 * @author zhengjun
 */
@Mapper
public interface StudentMapper {

    /**
     * 新增
     *
     * @param po 学生信息
     */
    void insert(StudentPO po);


    /**
     * 通过id 查询学生信息
     *
     * @param id 唯一id
     * @return 人信息
     */
    StudentPO selectById(@Param("id") String id);
}
