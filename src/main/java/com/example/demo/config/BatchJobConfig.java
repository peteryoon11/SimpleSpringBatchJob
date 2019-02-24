package com.example.demo.config;

import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

public class BatchJobConfig {
	@Configuration
	@EnableJpaRepositories("demo.example.demo.entity")
	@EnableTransactionManagement
	class JpaApplicationConfig {
	    private static final Logger logger = Logger
	            .getLogger(JpaApplicationConfig.class.getName());
	    @Bean
	    public AbstractEntityManagerFactoryBean entityManagerFactory() {
	        logger.info("Loading Entity Manager...");
	        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	        factory.setPersistenceUnitName("transactions-optional");
	        return factory;
	    }
	    @Bean
	    public PlatformTransactionManager transactionManager() {
	        logger.info("Loading Transaction Manager...");
	        JpaTransactionManager txManager = new JpaTransactionManager();
	        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
	        return txManager;
	    }
	    @Bean
	    public PersistenceExceptionTranslator persistenceExceptionTranslator() {
	        return new OpenJpaDialect();
	    }
	}
}