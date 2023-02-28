package com.hrms.demo;

import com.hrms.demo.mapper.StudentMapper;
import com.hrms.demo.po.StudentPO;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class ReadWriteSplitTest extends TestRunner {

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void testWrite() {
        StudentPO po = new StudentPO();
        po.setId("zhengjun01");
        po.setName("郑军");
        po.setAge(31);
        po.setGender(1);
        po.setSchoolName("w3cschool");
        po.setClassName("菜鸟训练营");
        studentMapper.insert(po);
    }

    @Test
    public void testRead() {
        StudentPO po = studentMapper.selectById("zhengjun01");
        Assert.assertNotNull(po);
    }

    @Test
    @Transactional
    public void testWriteAndRead(){
        testWrite();
        testRead();
    }

    @Test
    public void testUseWriteRead(){
        HintManager instance = HintManager.getInstance();
        //强制使用写库
        instance.setWriteRouteOnly();
        testRead();
        //设置自动读写分离
        instance.setReadwriteSplittingAuto();
    }
}
