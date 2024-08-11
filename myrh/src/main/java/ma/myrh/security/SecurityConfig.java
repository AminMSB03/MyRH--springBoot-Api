package ma.myrh.security;

import ma.myrh.entities.Agent;
import ma.myrh.entities.Company;
import ma.myrh.services.agentService.AgentService;
import ma.myrh.services.companyService.CompanyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

   private AgentService agentService;
   private CompanyService companyService;
   private PasswordEncoder passwordEncoder;



   public SecurityConfig(AgentService agentService, CompanyService companyService, PasswordEncoder passwordEncoder) {
      this.agentService = agentService;
      this.companyService = companyService;
      this.passwordEncoder = passwordEncoder;
   }

   @Bean
   UserDetailsService userDetailsService(){
      return email -> {
         Agent agent = this.agentService.Login(email);
         if (agent != null) {
            return new User(agent.getEmail(), agent.getPassword(),Collections.singletonList(new SimpleGrantedAuthority("AGENT")));
         }

         Company company = this.companyService.login(email);
         if (company != null) {
            return new User(company.getEmail(), company.getPassword(),Collections.singletonList(new SimpleGrantedAuthority("COMPANY")));
         }
         System.out.println("here"+"-".repeat(100));
         //throw new JwtExceptions("No user found for "+ email + ".");
         return null;
      };
   }


   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf(csrf->csrf.disable());
      http
              .cors().and()
              .authorizeHttpRequests()
              .requestMatchers("/offers/**", "/test/**","/api/companies/**","/api/accepted_offers/**")
              .permitAll()
              .and()
              //.authorizeHttpRequests(auth->auth.requestMatchers("/api/offers/**").hasRole("AGENT"))
              .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
               .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .httpBasic(Customizer.withDefaults());

      http.addFilter(new JwtAuthenticationFilter(authenticationManager(this.authenticationProvider(this.userDetailsService()))));
      http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
      return http.build();
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
      return new ProviderManager(Arrays.asList(authenticationProvider));
   }

   @Bean
   public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
      authenticationProvider.setUserDetailsService(userDetailsService);
      authenticationProvider.setPasswordEncoder(this.passwordEncoder);
      return authenticationProvider;
   }

   @Bean
   CorsConfigurationSource corsConfigurationSource()
   {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
      configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
   }

}
