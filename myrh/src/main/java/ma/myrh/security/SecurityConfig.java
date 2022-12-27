package ma.myrh.security;

import ma.myrh.entities.Agent;
import ma.myrh.entities.Company;
import ma.myrh.services.agentService.AgentService;
import ma.myrh.services.companyService.CompanyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    PasswordEncoder passwordEncoder;
    JwtFilter JwtFilter;
    CompanyService companyService;
    AgentService agentService;

    public SecurityConfig(PasswordEncoder passwordEncoder, ma.myrh.security.JwtFilter jwtFilter, CompanyService companyService, AgentService agentService) {
        this.passwordEncoder = passwordEncoder;
        JwtFilter = jwtFilter;
        this.companyService = companyService;
        this.agentService = agentService;
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    UserDetailsService loadUserByUsername() throws UsernameNotFoundException {
        return email->{

            Agent agent = this.agentService.Login(email);
            if(agent!=null)
                return new User(email,agent.getPassword(), Collections.singleton(new SimpleGrantedAuthority("AGENT")));

            Company company = this.companyService.login(email);
            if(company!=null)
                return new User(email,company.getPassword(), Collections.singleton(new SimpleGrantedAuthority("COMPANY")));

            throw new UsernameNotFoundException(email);
        };
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests().requestMatchers("/login/**").permitAll();
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(this.JwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }



}
