package next;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// next 외의 패키지를 사용하려면(ex> core.test
// next.config.WebMvcConfig 에서 어노테이션 처리해야 하지 않을지??
/*
@Configuration
@ComponentScan(basePackages={ "next", "core.test" })
@EnableAutoConfiguration
*/
//@SpringBootApplication(scanBasePackages={ "next.*", "core.test" })
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
