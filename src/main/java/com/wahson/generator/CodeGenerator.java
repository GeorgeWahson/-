package com.wahson.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

public class CodeGenerator {

    public static void main(String[] args) {
        // 获取代码生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();

        // 数据库设置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/info_management?serverTimezone=UTC");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("Wahson11736");
        autoGenerator.setDataSource(dataSourceConfig);

        // 设置全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("GeorgeWahson");             //设置作者
        globalConfig.setMapperName("%sDao");                 // 设置数据层接口名，%s为占位符，指代模块名称
        globalConfig.setIdType(IdType.AUTO);                 // 设置Id生成策略
        globalConfig.setFileOverride(true);                 // 设置是否覆盖原始生成的文件
        globalConfig.setOpen(false);                         // 设置生成完毕后是否打开生成代码所在的目录
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        autoGenerator.setGlobalConfig(globalConfig);

        // 设置包名相关配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.wahson");
        packageConfig.setEntity("domain");
        packageConfig.setMapper("dao");
        autoGenerator.setPackageInfo(packageConfig);

        // 策略设置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("tb_brand");   //设置当前参与生成的表名，参数为可变参数
        strategyConfig.setTablePrefix("tb_");    //设置数据库表的前缀名称，模块名 = 数据库表名 - 前缀名  例如： User = tbl_user - tbl_
        strategyConfig.setRestControllerStyle(true);  //设置是否启用Rest风格
        strategyConfig.setVersionFieldName("version");  //设置乐观锁字段名
        strategyConfig.setLogicDeleteFieldName("deleted");    //设置逻辑删除字段名
        strategyConfig.setEntityLombokModel(true);    //设置是否启用lombok
        autoGenerator.setStrategy(strategyConfig);

        //2.执行生成操作
        autoGenerator.execute();




    }




}
