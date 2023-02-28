package com.hrms.demo.other;

import com.google.common.collect.Range;
import org.apache.shardingsphere.infra.datanode.DataNodeInfo;
import org.apache.shardingsphere.sharding.algorithm.sharding.datetime.AutoIntervalShardingAlgorithm;
import org.apache.shardingsphere.sharding.algorithm.sharding.range.BoundaryBasedRangeShardingAlgorithm;
import org.apache.shardingsphere.sharding.algorithm.sharding.range.VolumeBasedRangeShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.text.ParsePosition;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Properties;

public class PartitionRangeTest {

    @Test
    public void testPartitionRange(){
        VolumeBasedRangeShardingAlgorithm algorithm = new VolumeBasedRangeShardingAlgorithm();
        Properties properties = new Properties();
        properties.setProperty("range-lower","1");
        properties.setProperty("range-upper","9");
        properties.setProperty("sharding-volume","2");
        Map<Integer, Range<Comparable<?>>> rangeMap = algorithm.calculatePartitionRange(properties);
        System.out.println(rangeMap);
    }

    @Test
    public void testPartitionRange_1(){
        BoundaryBasedRangeShardingAlgorithm algorithm = new BoundaryBasedRangeShardingAlgorithm();
        Properties properties = new Properties();
        properties.setProperty("sharding-ranges","10,40");
        Map<Integer, Range<Comparable<?>>> rangeMap = algorithm.calculatePartitionRange(properties);
        System.out.println(rangeMap);
    }

    @Test
    public void testFormat(){
        DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateValue = LocalDateTime.from(DATE_TIME_FORMAT.parse("2023-02-28 11:22:23", new ParsePosition(0)));
        System.out.println(dateValue);
    }

    @Test
    public void testAutoIntervalShardingAlgorithm(){
        AutoIntervalShardingAlgorithm algorithm = new AutoIntervalShardingAlgorithm();
        Properties properties = new Properties();
        properties.setProperty("datetime-lower","2023-02-28 00:00:00");
        properties.setProperty("datetime-upper","2023-03-01 23:59:59");
        properties.setProperty("sharding-seconds","86400");
        algorithm.init(properties);
        DataNodeInfo dataNodeInfo = new DataNodeInfo("person_",1,'0');
        PreciseShardingValue shardingValue = new PreciseShardingValue("hrms-test","create_time",dataNodeInfo,"2023-03-01 00:07:00");
        String sharding = algorithm.doSharding(Lists.newArrayList("person_0", "person_1", "person_2"), shardingValue);
        System.out.println(sharding);
    }

}
