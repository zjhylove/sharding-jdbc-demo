package com.hrms.demo.mapper;

import com.hrms.demo.po.PersonPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Person持久化服务
 *
 * @author zhengjun
 */
@Mapper
public interface PersonMapper {

    /**
     * 新增
     *
     * @param po 人信息
     */
    void insert(PersonPO po);


    /**
     * 通过id 查询人信息
     *
     * @param id 唯一id
     * @return 人信息
     */
    PersonPO selectById(@Param("id") String id);
}
