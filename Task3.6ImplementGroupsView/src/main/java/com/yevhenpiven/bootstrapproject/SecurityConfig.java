package com.yevhenpiven.bootstrapproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/login").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/student/**").hasRole("STUDENT")
                .requestMatchers("/teacher/**").hasRole("TEACHER")
                .requestMatchers("/staff/**").hasRole("STAFF")
                .anyRequest().authenticated())
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .permitAll())
            .logout(logout -> logout
                .permitAll())
            .csrf(csrf -> csrf.disable()); 

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();

        var admin = org.springframework.security.core.userdetails.User.withUsername("admin")
                .password(passwordEncoder().encode("admin")).roles("ADMIN").build();

        var student = org.springframework.security.core.userdetails.User.withUsername("student")
                .password(passwordEncoder().encode("student")).roles("STUDENT").build();

        var teacher = org.springframework.security.core.userdetails.User.withUsername("teacher")
                .password(passwordEncoder().encode("teacher")).roles("TEACHER").build();

        var staff = org.springframework.security.core.userdetails.User.withUsername("staff")
                .password(passwordEncoder().encode("staff")).roles("STAFF").build();

        userDetailsService.createUser(admin);
        userDetailsService.createUser(student);
        userDetailsService.createUser(teacher);
        userDetailsService.createUser(staff);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
