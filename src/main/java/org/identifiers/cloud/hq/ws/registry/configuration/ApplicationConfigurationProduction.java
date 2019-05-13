package org.identifiers.cloud.hq.ws.registry.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.configuration
 * Timestamp: 2019-05-13 16:05
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Configuration
@Profile("production")
@Slf4j
public class ApplicationConfigurationProduction implements WebMvcConfigurer {
    @Value("${org.identifiers.cloud.hq.ws.registry.cors.origin}")
    private String corsOrigins;

    @PostConstruct
    private void postConstruct() {
        log.info("[CONFIG] Production configuration enabled");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //log.info("CORS configuration for PRODUCTION");
        registry.addMapping("/**").allowedOrigins(corsOrigins).allowedMethods("*");
    }

    // Configure CORS for JPA Repositories
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurer() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.getCorsRegistry().addMapping("/restApi/**").allowedOrigins(corsOrigins).allowedMethods("*");
            }
        };
    }
}
