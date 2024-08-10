package com.programming3.devcompany.configuration.dataSourceConfig;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.repository.DeveloperRepository;
import com.programming3.devcompany.repository.JpaDeveloperRepositoryImpl;
import com.programming3.devcompany.repository.JpaRepDeveloperRepository;
import com.programming3.devcompany.service.DeveloperService;
import jakarta.persistence.EntityManager;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("jparep")
public class JpaRepDatabaseConfig {

    @Bean
    public DataSource dataSource() {

        return DataSourceBuilder
                .create()
                .url("jdbc:postgresql://localhost:5434/devcompany")
                .username("admin")
                .password("spring")
                .build();
    }
}
