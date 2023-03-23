package az.spring.hibernate.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "az.spring.hibernate")
@EnableTransactionManagement
public class SpringHibernateConfig {


    @Bean
    public DataSource dataSource(DataBaseConfig dataBaseConfig) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataBaseConfig.getDriverClassName());
        dataSource.setUrl(dataBaseConfig.getUrl());
        dataSource.setUsername(dataBaseConfig.getUsername());
        dataSource.setPassword(dataBaseConfig.getPassword());

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource((dataSource));
        localSessionFactoryBean.setPackagesToScan("az.spring.hibernate.model");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());

        return localSessionFactoryBean;
    }

    public PlatformTransactionManager platformTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    private Properties hibernateProperties() {

        Properties properties = new Properties();
        properties.setProperty(
                "hibernate.hbm2ddl.auto", "none");
        properties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        return properties;
    }

}
