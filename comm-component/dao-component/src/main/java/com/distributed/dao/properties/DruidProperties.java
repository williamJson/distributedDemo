package com.distributed.dao.properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * druid配置文件
 * see more info https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE%E5%B1%9E%E6%80%A7%E5%88%97%E8%A1%A8
 * @author wyq
 */
@ConfigurationProperties(prefix = "dataSource.druid")
public class DruidProperties {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
            String key = null;
            String value = null;
            try {
                key = item.getName();
                value = (String) item.get(item);
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
                logger.error("druid properties file has a problem with field:" + key, e);
                //系统推出
                System.exit(0);
            }
        }
        return properties;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(String initialSize) {
        this.initialSize = initialSize;
    }

    public String getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(String minIdle) {
        this.minIdle = minIdle;
    }

    public String getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }

    public String getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(String maxWait) {
        this.maxWait = maxWait;
    }

    public String getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public String getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getMaxEvictableIdleTimeMillis() {
        return maxEvictableIdleTimeMillis;
    }

    public void setMaxEvictableIdleTimeMillis(String maxEvictableIdleTimeMillis) {
        this.maxEvictableIdleTimeMillis = maxEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public String getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(String testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public String getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(String testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public String getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(String testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public String getPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(String poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public String getMaxOpenPreparedStatements() {
        return maxOpenPreparedStatements;
    }

    public void setMaxOpenPreparedStatements(String maxOpenPreparedStatements) {
        this.maxOpenPreparedStatements = maxOpenPreparedStatements;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getAsyncInit() {
        return asyncInit;
    }

    public void setAsyncInit(String asyncInit) {
        this.asyncInit = asyncInit;
    }
}
