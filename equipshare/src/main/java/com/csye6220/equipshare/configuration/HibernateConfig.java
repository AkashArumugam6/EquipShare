package com.csye6220.equipshare.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.csye6220.equipshare.model.Hardware;
import com.csye6220.equipshare.model.HardwareRequest;
import com.csye6220.equipshare.model.User;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory() {
        Map<String, Object> settings = new HashMap<>();
        settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/equipshare");
        settings.put("hibernate.connection.username", "root");
        settings.put("hibernate.connection.password", "p@ssword");
        settings.put("hibernate.hbm2ddl.auto", "update");
        settings.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); 
        settings.put("hibernate.show-sql", "true");

        
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);

        metadataSources.addPackage("com.csye6220.equipshare.model");
        metadataSources.addAnnotatedClasses(User.class, Hardware.class, HardwareRequest.class);

        Metadata metadata = metadataSources.buildMetadata();
        return metadata.getSessionFactoryBuilder().build();
    }
}
