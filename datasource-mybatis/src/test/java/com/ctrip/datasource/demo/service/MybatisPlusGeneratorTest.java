package com.ctrip.datasource.demo.service;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

import java.util.Collections;

public class MybatisPlusGeneratorTest {

    @Test
    public void generatorTest() {



    }

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://10.21.6.38:55111/dbadalclustertest01db", "w_dbadst01_vS", "aA1^HtLB5ppt0Xnq7yM3")
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
                // 策略配置
                .strategyConfig(builder -> builder.addInclude("dalservicetable"))
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker 或 Enjoy
                   .templateEngine(new BeetlTemplateEngine())
                .templateEngine(new EnjoyTemplateEngine())

                .templateEngine(new FreemarkerTemplateEngine())
                 */
                .globalConfig(builder -> builder.outputDir("/Users/liuhuanjie/Downloads/gener"))
                .execute();





//        FastAutoGenerator.create("mysql://10.21.6.38:55111/dbadalclustertest01db", "w_dbadst01_vS", "aA1^HtLB5ppt0Xnq7yM3")
//                .globalConfig(builder -> {
//                    builder.author("baomidou") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .outputDir("/Users/liuhuanjie/Downloads/gener"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.baomidou.mybatisplus") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "/Users/liuhuanjie/Downloads/gener")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("dalservicetable"); // 设置需要生成的表名
////                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
//                })
////                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
    }
}
