package org.http.backend.config;

import org.http.backend.component.CustomFormLoginSuccessHandler;
import org.http.backend.component.CustomOAuth2SuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
    private final CustomFormLoginSuccessHandler customFormLoginSuccessHandler;

    public SecurityConfig(CustomOAuth2SuccessHandler customOAuth2SuccessHandler, CustomFormLoginSuccessHandler customFormLoginSuccessHandler) {
        this.customOAuth2SuccessHandler = customOAuth2SuccessHandler;
        this.customFormLoginSuccessHandler = customFormLoginSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> {
                    auth.requestMatchers(("/admin")).hasRole("ADMIN");
                    auth.requestMatchers("/bookings").authenticated();
                    auth.requestMatchers("/movies","/users", "/bookings", "/schedules/**").permitAll();
                    auth.anyRequest().denyAll();
                })
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(customOAuth2SuccessHandler)
                        .defaultSuccessUrl("http://localhost:5173/", true))
                .formLogin(form -> form
                        .successHandler(customFormLoginSuccessHandler));
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
