package com.InternationalPassport.springConfigs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.InternationalPassport.businessLayer.DAO"),
        @ComponentScan("com.InternationalPassport.businessLayer.service")})
@PropertySource("classpath:application.properties")
//@EnableJpaRepositories("com.InternationalPassport.businessLayer.DAO") // need turn on just for test
public class SpringDataConfig {

//    private Properties properties;

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
//        factoryBean.setPackagesToScan(new String [] {"com.InternationalPassport.businessLayer.model"});

        // Just want test
        factoryBean.setPackagesToScan("com.InternationalPassport.businessLayer.model");

        final JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setJpaProperties(getJpaProperties());

        return factoryBean;
    }

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        return dataSource;
//    }

    //for auto load prop
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(getEntityManagerFactoryBean().getObject());

        return jpaTransactionManager;
    }

//    public void loadProperty() {
//        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        String configPath = rootPath + "application.properties";
//        properties = new Properties();
//        try{
//            properties.load(new FileInputStream(configPath));
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(properties.toString()+  " Priperties");
//
//    }

    public Properties getJpaProperties() {
        final Properties pr = new Properties();
        pr.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        pr.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        pr.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        pr.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        pr.put("hibernate.generate_statistics", env.getProperty("hibernate.generate_statistics"));

        return pr;
    }
}
