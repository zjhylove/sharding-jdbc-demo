package com.hrms.demo;

import cn.hutool.core.lang.UUID;
import com.hrms.demo.mapper.PersonMapper;
import com.hrms.demo.po.PersonPO;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class ShardingDatabaseAndTableTest extends TestRunner {

    @Resource
    private PersonMapper personMapper;

    @Test
    public void testInsert() {
        PersonPO po = new PersonPO();
        po.setId(UUID.fastUUID().toString());
        po.setName("郑军");
        po.setAge(31);
        po.setGender(1);
        personMapper.insert(po);
    }

    @Test
    public void testSelectById() {
        PersonPO personPo = personMapper.selectById("29e35a28-2215-448d-9d88-37c040d13766");
        Assert.assertNotNull(personPo);
    }

    @Test
    @Transactional
    public void testInsertAndRead() {
        PersonPO po = new PersonPO();
        String id = UUID.fastUUID().toString();
        po.setId(id);
        po.setName("郑军");
        po.setAge(31);
        po.setGender(1);
        personMapper.insert(po);
        PersonPO dbPo = personMapper.selectById(id);
        Assert.assertNotNull(dbPo);
    }

    @Test
    public void testInsertAndRead_1() {
        PersonPO po = new PersonPO();
        String id = UUID.fastUUID().toString();
        po.setId(id);
        po.setName("郑军");
        po.setAge(31);
        po.setGender(1);
        personMapper.insert(po);

        HintManager instance = HintManager.getInstance();
        PersonPO dbPo = personMapper.selectById(id);
        instance.setReadwriteSplittingAuto();
        Assert.assertNotNull(dbPo);
    }

    @Test
    public void testHintInline() {
        HintManager instance = HintManager.getInstance();
        PersonPO po = new PersonPO();
        String id = UUID.fastUUID().toString();
        //instance.addDatabaseShardingValue();
        instance.addTableShardingValue("person", Math.abs(id.hashCode()) % 3);
        po.setId(id);
        po.setName("郑军");
        po.setAge(31);
        po.setGender(1);
        personMapper.insert(po);
        instance.clearShardingValues();
    }
}
