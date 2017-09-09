package com.zmj.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan("com.zmj.demo.dao")
public class MyBatisConfig {
    private static Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

    @Autowired
    private JdbcConfig jdbcConfig;

    @Bean
    public DataSource createDataSource() throws SQLException {
        return DataSourceBuilder.create(Thread.currentThread().getContextClassLoader())
                .driverClassName(jdbcConfig.driverClass)
                .url(jdbcConfig.url)
                .username(jdbcConfig.username)
                .password(jdbcConfig.password)
                .build();
    }

    @PropertySource(value = "application.yml")
    @Component
    static class JdbcConfig {
        /** 数据库用户名  */
        @Value("${spring.datasource.username}")
        private String username;
        /** 驱动名称    */
        @Value("${spring.datasource.driver-class-name}")
        private String driverClass;
        /** 数据库连接url    */
        @Value("${spring.datasource.url}")
        private String url;
        /**     数据库密码   */
        @Value("${spring.datasource.password}")
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDriverClass() {
            return driverClass;
        }

        public void setDriverClass(String driverClass) {
            this.driverClass = driverClass;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
