package cn.xzxy.lewy.dscross.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SnowflakeConfig {

    @Bean("playerKeyGenerator")
    public SnowflakeShardingKeyGenerator playerKeyGenerator() {
        return new SnowflakeShardingKeyGenerator();
    }

    @Bean("clubKeyGenerator")
    public SnowflakeShardingKeyGenerator clubKeyGenerator() {
        return new SnowflakeShardingKeyGenerator();
    }

}
