package ke.co.creswave.uzimahealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@EnableAutoConfiguration
@SpringBootApplication
public class UzimaHealthApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(UzimaHealthApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UzimaHealthApplication.class);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
