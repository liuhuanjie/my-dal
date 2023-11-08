package com.ctrip.datasource.demo.config;


import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.ctrip.datasource.configure.DalDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.ctrip.datasource.demo.mapper.dbadalclustertest01db", sqlSessionFactoryRef = "dalDbSessionFactory")
public class DalDbMybatisConfig {

    @Bean("dalDbSessionFactory")
    public SqlSessionFactory getDalDbSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        DalDataSourceFactory dalDataSourceFactory = new DalDataSourceFactory();

        sqlSessionFactoryBean.setDataSource(dalDataSourceFactory.getOrCreateDataSource("dbadalclustertest01db_dalcluster"));
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResource("classpath:mappers/dbadalclustertest01db/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }
}
