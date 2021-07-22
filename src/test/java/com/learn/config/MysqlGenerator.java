package com.learn.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * MySQL代码生成器
 *
 * @author wangxl
 * @date 2021/7/16 8:56
 */
public class MysqlGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        // 作者
        globalConfig.setAuthor("wangxl");
        // 是否打开资源管理器
        globalConfig.setOpen(false);
        // 实体属性 Swagger2 注解
        globalConfig.setSwagger2(true);
        // 覆盖文件
        globalConfig.setFileOverride(true);
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/learn?useSSL=true&useUnicode=true&characterEncoding=utf8" +
                "&serverTimezone=PRC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        // 父包
        packageConfig.setParent("com.learn");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setMapper("mapper");
        packageConfig.setEntity("entity");
        autoGenerator.setPackageInfo(packageConfig);

        // 自定义配置
        autoGenerator.setCfg(getInjectionConfig());

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 实体类名称驼峰命名
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 列名名称驼峰命名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // 使用简化getter和setter
        strategyConfig.setEntityLombokModel(true);
        // 驼峰转连字符
        strategyConfig.setControllerMappingHyphenStyle(true);
        // 使用 restController
        strategyConfig.setRestControllerStyle(true);
        autoGenerator.setStrategy(strategyConfig);

        // 设置模板引擎
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }

    /**
     * 自定义配置，只生成实体类，xml放在resource下
     *
     * @return InjectionConfig
     */
    private static InjectionConfig getInjectionConfig() {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        // 覆盖 entity文件
        injectionConfig.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建,这里调用默认的方法
                checkDir(filePath);
                // 对于已存在的文件，只需重复生成 entity
                File file = new File(filePath);
                boolean exist = file.exists();
                if (exist) {
                    return FileType.ENTITY == fileType;
                }
                // 不存在的文件都需要创建
                return true;
            }
        });

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return System.getProperty("user.dir") + "/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        injectionConfig.setFileOutConfigList(focList);

        return injectionConfig;
    }
}
