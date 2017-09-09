package com.zmj.demo.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

//@Configuration
public class SessionFactoryConfig {
    /** Mybaits配置路径 */
    private static String MYBATIS_CONFIG = "./mybatis-config.xml";
    /** mybatis mapper source 路径    */
    private static String MAPPER_PATH = "com/zmj/demo/mapper/**Mapper.xml";

    @Autowired
    private DataSource dataSource;

    /** typeAliasPackage,实体类    */
    private String typeAliasPackage = "com.zmj.demo.domain";

    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSession = new SqlSessionFactoryBean();
        /** 设置mybatis configuration 扫描路径    */
        sqlSession.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        /** 添加mapper扫描路径    */
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
        sqlSession.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
        /** 设置datasource    */
        sqlSession.setDataSource(dataSource);
        /** 设置typeAlias 包扫描路径   */
        sqlSession.setTypeAliasesPackage(typeAliasPackage);
        return sqlSession;
    }
}
