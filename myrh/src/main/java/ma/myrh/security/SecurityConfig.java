package ma.myrh.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    PasswordEncoder passwordEncoder;
    JwtFilter JwtFilter;

    public SecurityConfig(PasswordEncoder passwordEncoder, JwtFilter jwtFilter) {
        this.passwordEncoder = passwordEncoder;
        this.JwtFilter = jwtFilter;
    }


    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    UserDetailsService loadUserByUsername() throws UsernameNotFoundException {
        return email->{
            Map<String, String> map = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            map.put("amine",this.passwordEncoder.encode("amine123"));
            map2.put("amine2",this.passwordEncoder.encode("amine1234"));

            if(map.containsKey(email))
                return new User(email,map.get(email),new ArrayList<>());
            else if(map2.containsKey(email))
                return new User(email,map2.get(email),new ArrayList<>());
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
