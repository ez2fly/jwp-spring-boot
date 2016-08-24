package next.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SuppressWarnings("deprecation")
@EnableAutoConfiguration 
@Configuration
@EntityScan("next.model")
@EnableJpaRepositories("next.repository")
@EnableTransactionManagement
public class RepositoryConfiguration {
	
}
