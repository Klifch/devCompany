package com.programming3.devcompany.configuration.dataSourceConfig;

import com.programming3.devcompany.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("jpa")
public class JpaDatabaseConfig {

    @Bean
    public DeveloperRepository developerRepository(EntityManager entityManager) {
        return new JpaDeveloperRepositoryImpl(entityManager);
    }

    @Bean
    public ProjectRepository projectRepository(EntityManager entityManager) {
        return new JpaProjectRepositoryImpl(entityManager);
    }

    @Bean
    public DataSource dataSource() {

        return DataSourceBuilder
                .create()
                .url("jdbc:postgresql://localhost:5432/devcompany")
                .username("postgres")
                .password("1789")
                .build();
    }
}

//.driverClassName("org.postgresql.Driver")
