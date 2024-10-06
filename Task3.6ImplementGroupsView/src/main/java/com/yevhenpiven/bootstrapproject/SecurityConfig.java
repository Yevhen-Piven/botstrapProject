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
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/", "/login").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/student/**")
                .hasAnyRole("STUDENT", "ADMIN").requestMatchers("/teacher/**").hasAnyRole("TEACHER", "ADMIN")
                .requestMatchers("/staff/**").hasAnyRole("STAFF", "ADMIN").requestMatchers("/courses/**")
                .hasAnyRole("ADMIN", "STAFF", "STUDENT", "TEACHER").requestMatchers("/groups/**")
                .hasAnyRole("ADMIN", "STAFF", "STUDENT", "TEACHER").requestMatchers("/classrooms/**")
                .hasAnyRole("ADMIN", "STAFF", "STUDENT", "TEACHER").requestMatchers("/departments/**")
                .hasAnyRole("ADMIN", "STAFF", "STUDENT", "TEACHER").requestMatchers("/timetables/**")
                .hasAnyRole("ADMIN", "STAFF", "STUDENT", "TEACHER").anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.loginPage("/login").permitAll()).logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
}
