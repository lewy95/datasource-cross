package cn.xzxy.lewy.dscross;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class DatasourceCrossApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatasourceCrossApplication.class, args);
    }

}
