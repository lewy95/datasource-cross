package cn.xzxy.lewy.dscross.common.aspectj;

import cn.xzxy.lewy.dscross.common.annotation.DataSource;
import cn.xzxy.lewy.dscross.common.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源切面处理
 * @author lewy95
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect {

    // 基于注解的切面
    @Pointcut("@annotation(cn.xzxy.lewy.dscross.common.annotation.DataSource)")
    public void dsPointCut() {
    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();

        Method method = signature.getMethod();

        DataSource dataSource = method.getAnnotation(DataSource.class);
        if (dataSource != null) {
            if ("DYNAMIC".equals(dataSource.value().name())) {
                Object[] args = point.getArgs();
                if (args.length > 0 && args[0] != null) {
                    String dataSourceId = args[0].toString();
                    DynamicDataSourceContextHolder.setDataSourceType(dataSourceId);
                }
            } else {
                DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
            }
        }
        try {
            return point.proceed();
        } finally {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }
}
