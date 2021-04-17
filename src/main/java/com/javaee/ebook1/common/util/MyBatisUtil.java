package com.javaee.ebook1.common.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis工具类
 *
 * @author liuhaoyu
 *
 */
public class MyBatisUtil {

    public static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 从类路径下加载资源文件mybatis-config.xml
            String resource = "D:\\0dasanxia\\JavaEE\\JavaEEProject1\\src\\main\\resources\\application.properties";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 由 SqlSessionFactoryBuilder创建SqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 由 SqlSessionFactory创建SqlSession
     *
     * @return
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 关闭SqlSession
     *
     * @param sqlSession
     */
    public static void closeSqlSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

}