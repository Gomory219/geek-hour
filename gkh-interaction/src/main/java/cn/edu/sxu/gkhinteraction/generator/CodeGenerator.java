package cn.edu.sxu.gkhinteraction.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://106.54.215.151:5433/gkh-all";
        String username = "postgres";
        String password = System.getenv("POSTGRES_PASSWORD");

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
                            .outputDir("D:\\code\\geek-hour\\gkh-interaction\\src\\main\\java"); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("cn.edu.sxu.gkhinteraction.generator") // 设置父包名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\code\\geek-hour\\gkh-interaction\\src\\main\\resources\\mapper")) // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder
                                .addInclude("tb_like")
                                .addInclude("tb_comment")
                                .addTablePrefix("tb_")
                                .entityBuilder().enableLombok()
                )

                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
