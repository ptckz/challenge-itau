package br.com.ptck.app.presenter.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"br.com.ptck.app.data.entities"})
@EnableJpaRepositories(basePackages = {"br.com.ptck.app.data.repositories"})
public class H2Config {
    
}
