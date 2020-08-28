package cn.xzxy.lewy.dscross;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.xzxy.lewy.dscross.mapper") //扫描的mapper
@SpringBootApplication
public class DatasourceCrossApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatasourceCrossApplication.class, args);
    }

}
