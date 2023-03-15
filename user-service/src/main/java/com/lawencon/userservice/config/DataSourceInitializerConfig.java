package com.lawencon.userservice.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DataSourceInitializerConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        boolean tableExist = false;
        try {
            final Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
            tableExist = count != null && count > 0;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        if (!tableExist) {
            System.out.println("Data Init Not Exist");
            initializer.setDatabasePopulator(databasePopulator());
        } else {
            System.out.println("Data Init Exist");
        }
        return initializer;
    }
    
    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        System.out.println("Loading script: " + new ClassPathResource("database/data-init.sql").getPath());
        populator.addScript(new ClassPathResource("database/data-init.sql"));
        return populator;
    }
}