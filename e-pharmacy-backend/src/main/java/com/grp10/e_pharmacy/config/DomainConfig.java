package com.grp10.e_pharmacy.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.grp10.e_pharmacy.domain")
@EnableJpaRepositories("com.grp10.e_pharmacy.repos")
@EnableTransactionManagement
public class DomainConfig {
}
