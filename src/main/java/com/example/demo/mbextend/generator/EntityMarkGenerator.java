package com.example.demo.mbextend.generator;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Pet;
import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 创建实体辅助类
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/20 16:49
 */
@Slf4j
public class EntityMarkGenerator {
    // 原码路径
    private static final String sourceDir = "src/main/java/";
    // 扩展包路径
    private static final String mbextend = "com/example/demo/mbextend";
    // 要生成标记类的实体类对象
    private static final List<Class<?>> entityClasses = Arrays.asList(User.class, Pet.class, Employee.class);

    public static void main(String[] args){
        String extendPath = sourceDir+mbextend;
        File directory = new File(extendPath);
        Path path;
        try {
            path = Paths.get(directory.getCanonicalPath());
        } catch (IOException e) {
            log.error("目录({})获取失败",directory.getAbsolutePath());
            return;
        }
        // 生成实体类辅助类java文件
        for (Class<?> tClass : entityClasses) {
            String tableName;
            TableName tableNameAnno = tClass.getAnnotation(TableName.class);
            if(tableNameAnno==null || tableNameAnno.value().equals("")){
                tableName = camelConvert(tClass.getSimpleName());
            }else{
                tableName = tableNameAnno.value();
            }
            String markTemplate;
            Path filePath = Paths.get(extendPath + "/generator/MarkTemplate.txt");
            try {
                List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
                markTemplate = String.join("\n", lines);
                markTemplate = markTemplate.replaceAll("\\$path\\$", mbextend.replaceAll("/", "."));
                markTemplate = markTemplate.replaceAll("\\$entity\\$", tClass.getSimpleName());
                markTemplate = markTemplate.replaceAll("\\$table\\$", tableName);
                StringBuilder fieldsDeclare = new StringBuilder();
                StringBuilder fieldInits = new StringBuilder();
                List<Field> fields = new ArrayList<>(Arrays.asList(tClass.getDeclaredFields()));
                Class<?> superclass = tClass.getSuperclass();
                if(superclass!=null) {
                    fields.addAll(Arrays.asList(superclass.getDeclaredFields()));
                }
                List<String> qFields = new ArrayList<>();
                fields.forEach(f->{
                    TableField tableField = f.getAnnotation(TableField.class);
                    if(tableField==null || tableField.exist()) {
                        String column;
                        if(tableField!=null && !tableField.value().equals("")){
                            column = tableField.value().toLowerCase();
                        }else{
                            column = camelConvert(f.getName());
                        }
                        fieldsDeclare.append(String.format("\tpublic QField %s;\n", f.getName()));
                        fieldInits.append(String.format("\n\t\tthis.%s = new QField(null,\"%s\",null,columnPrefix);",
                                f.getName(), column));
                        qFields.add(f.getName());
                    }
                });
                markTemplate = markTemplate.replaceAll("\\$fieldsDeclare\\$", fieldsDeclare.toString());
                markTemplate = markTemplate.replaceAll("\\$fieldInits\\$", fieldInits.toString());
                markTemplate = markTemplate.replaceAll("\\$sqlFields\\$", String.join(",",qFields));
            } catch (IOException e) {
                log.error("文件：{}，读取失败",filePath.toAbsolutePath(),e);
                return;
            }

            File javaFile = new File(path.toAbsolutePath()+"/Q"+tClass.getSimpleName()+".java");
            try {
                Files.deleteIfExists(javaFile.toPath());
            } catch (IOException e) {
                log.error("文件({})删除失败。",javaFile.getAbsolutePath());
                return;
            }
            try {
                Files.createFile(javaFile.toPath());
            } catch (IOException e) {
                log.error("文件({})创建失败。",javaFile.getAbsolutePath());
                return;
            }
            try {
                Files.write(javaFile.toPath(), Collections.singletonList(markTemplate), StandardCharsets.UTF_8);
            } catch (IOException e) {
                log.error("文件({})写入失败。",javaFile.getAbsolutePath());
            }
        }
    }

    //首字母变小写，其余大写字母变成下划线加小写，如：UserName -> user_name
    private static String camelConvert(String targetStr){
        char[] chars = targetStr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(Character.isUpperCase(chars[i])){
                targetStr = targetStr.replaceFirst(Character.toString(chars[i]),
                        (i == 0 ? "" : "_") + Character.toLowerCase(chars[i]));
            }
        }
        return targetStr;
    }
}
