package com.programming3.devcompany.configuration.dataSourceConfig;

import com.programming3.devcompany.repository.*;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@Profile("h2")
public class H2DatabaseConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DeveloperRepository developerRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcDeveloperRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public ProjectRepository projectRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcProjectRepository(jdbcTemplate);
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:devcompany")
                .username("sa")
                .password("");

        return dataSourceBuilder.build();
    }
}
