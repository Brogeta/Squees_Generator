package squees_generator.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Brandon.O'Donnell on 3/14/2017.
 */

    @Configuration
    @EnableAutoConfiguration
    @EntityScan(basePackages = "squees_generator")
    @EnableJpaRepositories(basePackages = "squees_generator")
    @EnableTransactionManagement
    public class RepositoryConfiguration {
    }

