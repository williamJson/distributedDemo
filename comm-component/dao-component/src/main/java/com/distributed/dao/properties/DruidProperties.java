package com.distributed.dao.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * druid配置文件
 *
 * @author wyq
 */
@ConfigurationProperties(prefix = "dataSource.druid")
public class DruidProperties {

    //基础信息

    /**
     * 数据库地址 用户名 密码
     */
    private String url;

    private String username;

    private String password;


    //其他配置


    /**
     * 配置初始化大小、最小、最大
     */
    private String initialSize;

    private String minIdle;

    private String maxActive;

    /**
     * 配置获取连接等待超时的时间
     */
    private String maxWait;


    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    private String timeBetweenEvictionRunsMillis;

    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    private String minEvictableIdleTimeMillis;

    private String maxEvictableIdleTimeMillis;


    /**
     * select 1
     */
    private String validationQuery;

    private String testWhileIdle;

    private String testOnBorrow;

    private String testOnReturn;

    private String poolPreparedStatements;

    private String maxOpenPreparedStatements;

    /**
     * 配置监控统计拦截的filters
     */
    private String filters;

    private String asyncInit;


    /**
     * bean属性转存properties
     *
     * @return
     */
    public Properties toProperties() {
        Field[] fields = this.getClass().getDeclaredFields();
        Properties properties = new Properties();

        if (fields.length == 0) {
            return properties;
        }
        for (Field item : fields) {
            try {
                String key = item.getName();
                String value = (String) item.get(item);
                //如果属性值为空，则不进行转换
                if (null == value || value.length() == 0) {
                    continue;
                }
                properties.put(key, value);
            } catch (IllegalAccessException e) {
                //这里我觉得如果出现异常获取异常应该终止程序
                //但是因为不重要的配置而终止程序又是没有必要的
                //所以这里很纠结该怎么处理

                //最终处理方式
                //打印error日志，提示配置文件出错

                //系统推出
                System.exit(0);
            }
        }
        return properties;

    }

}
