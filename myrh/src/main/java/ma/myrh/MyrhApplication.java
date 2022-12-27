package ma.myrh;

import ma.myrh.dtos.AgentRepository;
import ma.myrh.dtos.CompanyRepository;
import ma.myrh.entities.Agent;
import ma.myrh.entities.Company;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

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
}
