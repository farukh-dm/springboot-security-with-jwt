package demo.springboot.securitywithjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringbootSecurityWithJwtApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSecurityWithJwtApplication.class, args);
	}
	
	// The first step in producing a deployable war file is to provide a 
	// SpringBootServletInitializer subclass and override its configure() method. 
	// This makes use of Spring Framework’s Servlet 3.0 support and allows you to 
	// configure your application when it’s launched by the servlet container.
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringbootSecurityWithJwtApplication.class);
	}

}
