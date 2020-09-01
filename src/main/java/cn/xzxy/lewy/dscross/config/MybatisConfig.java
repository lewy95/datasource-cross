package cn.xzxy.lewy.dscross.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * mybatis 注解配置
 */
@Configuration
// 表示通过aop框架暴露该代理对象，AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 指定要扫描的Mapper类的包的路径
@MapperScan("cn.xzxy.lewy.dscross.mapper")
public class MybatisConfig {

}
