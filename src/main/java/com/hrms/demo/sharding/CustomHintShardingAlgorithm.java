package com.hrms.demo.sharding;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.Properties;

/**
 * 自定义精确算法
 * @author zhengjun
 */
public class CustomHintShardingAlgorithm implements HintShardingAlgorithm<Integer> {

    /**
     * 可以定义算法所需要的配置
     */
    private Properties props;

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Integer> shardingValue) {

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
