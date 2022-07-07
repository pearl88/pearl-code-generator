package com.pearl.generator.java;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.pearl.generator.java.mq.MybatisFreemarkerTemplateEngine;
import org.springframework.util.StringUtils;

import java.util.Scanner;

/**
 * @author TangDan
 * @version 1.0
 * @since 2022/6/29
 */
public class Generator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!StringUtils.isEmpty(ipt)) {
                return ipt;
            }
        }
        throw new RuntimeException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 全局策略配置
        GlobalConfig globalConfig = GeneratorBuilder.globalConfigBuilder()
                // 覆盖已生成文件
                .fileOverride()
                // 生成后是否打开生成目录
                .openDir(true)
                // 指定输出目录 默认值: windows:D:// linux or mac : /tmp
                .outputDir("D:\\output")
                // 生成swagger注解
                .enableSwagger()
                // 作者名
                .author("pearl")
                // 时间策略
                .dateType(DateType.TIME_PACK)
                // 注释日期格式
                .commentDate("yyyy-MM-dd")
                .build();

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig
                // 驱动连接的URL、数据库连接用户名、数据库连接密码
                .Builder("jdbc:mysql://127.0.0.1:3306/angel_admin?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai", "root", "123456")
                // 类型转换,数据库=》JAVA类型
                .typeConvert(new MySqlTypeConvert())
                // 关键字处理 ,这里选取了mysql5.7文档中的关键字和保留字（含移除）
                .keyWordsHandler(new MySqlKeyWordsHandler())
                // 数据库信息查询类,默认由 dbType 类型决定选择对应数据库内置实现
                .dbQuery(new MySqlQuery())
                // 数据库 schema name
                .schema("mybatis-plus")
                .build();

        // 包配置
        PackageConfig packageConfig = new PackageConfig.Builder()
                // 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
                .parent(scanner("父包名"))
                // 父包模块名
                .moduleName(scanner("模块名"))
                .build();

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig.Builder()
                //指定自定义模板路径, 位置：/resources/templates/entity2.java.ftl(或者是.vm)
                //注意不要带上.ftl(或者是.vm), 会根据使用的模板引擎自动识别
                .build();

        // 设置自定义模板引擎
        AbstractTemplateEngine templateEngine = new MybatisFreemarkerTemplateEngine();


        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .build();

        // 注入配置
        //new InjectionConfig().beforeOutputFile();

        ConfigBuilder configBuilder=new ConfigBuilder(packageConfig,dataSourceConfig,strategyConfig,templateConfig,globalConfig,null);

        // 添加以上配置到AutoGenerator中
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig); // 数据源配置
        autoGenerator.global(globalConfig); // 全局策略配置
        autoGenerator.packageInfo(packageConfig);    // 包配置
        autoGenerator.template(templateConfig); // 配置模板
        // 生成代码,传入模板
        autoGenerator.execute(templateEngine);
    }
}
