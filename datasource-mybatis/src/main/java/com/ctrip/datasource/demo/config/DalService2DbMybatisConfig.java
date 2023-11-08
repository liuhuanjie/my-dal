package com.ctrip.datasource.demo.config;


import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.ctrip.datasource.configure.DalDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.ctrip.datasource.demo.mapper.dalservice2db", sqlSessionFactoryRef = "dalService2DbSessionFactory")
public class DalService2DbMybatisConfig {


    @Bean("dalService2DbSessionFactory")
    public SqlSessionFactory getDalDbSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        DalDataSourceFactory dalDataSourceFactory = new DalDataSourceFactory();

        sqlSessionFactoryBean.setDataSource(dalDataSourceFactory.getOrCreateDataSource("dalservice2db_dalcluster"));
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResource("classpath:mappers/dbadalclustertest01db/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }
}
