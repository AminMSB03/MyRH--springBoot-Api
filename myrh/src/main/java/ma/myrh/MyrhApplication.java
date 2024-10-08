package ma.myrh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


@SpringBootApplication
public class MyrhApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyrhApplication.class, args);
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


		/*
			@Bean @Transactional
			CommandLineRunner commandLineRunner(AgentRepository agentRepository, CompanyRepository companyRepository){
					return args->{
						Agent agent = new Agent();
						agent.setEmail("agent@agent.com");
						agent.setPassword(this.passwordEncoder().encode("agent123"));
						agentRepository.save(agent);

						Company company = new Company();
						company.setEmail("youcode@gmail.com");
						company.setPassword(this.passwordEncoder().encode("youcode123"));
						company.setPhoneNumber("098373992");

						companyRepository.save(company);
					};

			}
		 */

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
