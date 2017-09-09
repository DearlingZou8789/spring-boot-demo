package com.zmj.demo.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.io.IOException;

public class SessionSingleton {
    private static SessionSingleton instance;

    /**
     * SessionSignleton单例对象
     */
    private SessionSingleton(){
        SessionFactoryConfig config = new SessionFactoryConfig();
        try {
            SqlSessionFactoryBean bean = config.createSqlSessionFactoryBean();
            try {
                SqlSessionFactory factory = bean.getObject();
                this.sqlSession = factory.openSession();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private SqlSession sqlSession;

    /**
     * 线程安全的单例获取方法
     * @return  SessionSingleton单例对象
     */
    public static synchronized SessionSingleton getInstance(){
        if (instance == null) {
            instance = new SessionSingleton();
        }
        return instance;
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
