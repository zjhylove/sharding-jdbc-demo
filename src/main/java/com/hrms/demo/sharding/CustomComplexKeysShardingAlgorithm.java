package com.hrms.demo.sharding;

import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.Properties;

/**
 * 自定义复合算法
 *
 * @author zj
 */
public class CustomComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<String> {

    /**
     * 可以定义算法所需要的配置
     */
    private Properties props;

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<String> shardingValue) {
        // todo
        return null;
    }

    @Override
    public Properties getProps() {
        return props;
    }

    @Override
    public void init(Properties properties) {
        props = properties;
    }
}
