package sk.stuba.bachelorProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ServletComponentScan
@SpringBootApplication
@EnableJpaAuditing
public class BachelorProjectApplication  extends SpringBootServletInitializer {

	/**
	 * This method allows the app to run on a provided Tomcat server
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BachelorProjectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(BachelorProjectApplication.class, args);
	}

}
