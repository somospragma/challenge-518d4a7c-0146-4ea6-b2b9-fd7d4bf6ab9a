package com.fintech.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import com.fintech.loans.application.services.LoanService;
import com.fintech.loans.infrastructure.adapters.LoanJpaAdapter;
import com.fintech.loans.infrastructure.config.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.fintech.loans.infrastructure.persistence")
@EntityScan(basePackages = "com.fintech.loans.infrastructure.persistence")
@EnableConfigurationProperties
@EnableAsync
@ComponentScan(basePackages = {"com.fintech.loans"})
public class LoansApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LoansApplication.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public LoanService loanService(LoanJpaAdapter loanJpaAdapter) {
        return new LoanService(loanJpaAdapter);
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}