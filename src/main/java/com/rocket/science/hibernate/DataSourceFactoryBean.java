package com.rocket.science.hibernate;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by sinraja on 2/24/17.
 */
public class DataSourceFactoryBean extends AbstractFactoryBean<DataSource> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceFactoryBean.class);

    private String url;
    private String user;
    private String password;
    private String driver;

    private int maxSize = 8;
    private int minSize = 1;
    private int initialSize = 1;

    private String validationQuery;

    public DataSourceFactoryBean(String url, String user, String password, String driver) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.driver = driver;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    @Override
    public boolean isSingleton(){
        return true;
    }

    @Override
    public Class<?> getObjectType() {
        return DataSource.class;
    }

    @Override
    protected void destroyInstance(DataSource instance) throws SQLException {
        ((BasicDataSource) instance).close();
    }

    @Override
    protected DataSource createInstance() throws Exception {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxSize);
        dataSource.setMinIdle(minSize);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setValidationQuery(validationQuery);

        LOGGER.info("Starting DataSource with following connection settings :");
        LOGGER.info("========================================================");
        LOGGER.info("Driver:         " + driver);
        LOGGER.info("URL:            " + url);
        LOGGER.info("User:           " + user);
        LOGGER.info("Min Size:       " + minSize);
        LOGGER.info("Max Size:       " + maxSize);
        LOGGER.info("Initial Size:   " + initialSize);
        LOGGER.info("Validation:     " + validationQuery);
        LOGGER.info("=========================================================");

        return dataSource;
    }
}
