package com.hrms.demo.sharding;

import org.apache.shardingsphere.infra.datanode.DataNodeInfo;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;

/**
 * 自定义标准分片算法
 *
 * @author zhengjun
 */
public class CustomStandardShardingAlgorithm implements StandardShardingAlgorithm<String> {

    /**
     * 可以定义算法所需要的配置
     */
    private Properties props;

    /**
     * 自定义属性
     */
    private static final String SHARDING_COUNT_KEY = "sharding-count";

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        // 自定义属性
        int shardingCount = Integer.parseInt(props.getProperty(SHARDING_COUNT_KEY));
        String suffix = String.valueOf(Math.abs(shardingValue.getValue().hashCode()) % shardingCount);
        return findMatchedTargetName(availableTargetNames, suffix, shardingValue.getDataNodeInfo()).orElse(null);
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<String> shardingValue) {
        return availableTargetNames;
    }

    @Override
    public Properties getProps() {
        return props;
    }

    @Override
    public void init(Properties properties) {
        this.props = properties;
    }

    public void setProps(Properties props) {
        this.props = props;
    }
}
