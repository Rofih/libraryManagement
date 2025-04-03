package com.rofihLibrary.libraryManagement.config;

//import com.rofihLibrary.libraryManagement.filters.JwtRequestFilter;
//import com.rofihLibrary.libraryManagement.services.UserServiceImpl;
//import com.rofihLibrary.libraryManagement.services.UserServiceInterface;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.bind.annotation.RequestBody;

@Configuration
//@RequiredArgsConstructor
public class SecurityConfig {
//    @Lazy
//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;
//    @Autowired
//    private UserServiceInterface userDetailsService;

//    @Autowired
//    public void setJwtRequestFilter(JwtRequestFilter jwtRequestFilter) {
//        this.jwtRequestFilter = jwtRequestFilter;
//    }
//
//    @Autowired
//    public void setUserDetailsService(UserServiceImpl userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//    @Autowired
//    public SecurityConfig(JwtRequestFilter jwtRequestFilter, UserServiceImpl userDetailsService) {
//        this.jwtRequestFilter = jwtRequestFilter;
//        this.userDetailsService = userDetailsService;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(UserServiceInterface userDetailsService, PasswordEncoder passwordEncoder) {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService((UserDetailsService) userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder);
//
//        return authProvider;
//    }
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/user/create", "/user/login").permitAll()
//                        .requestMatchers("/book/**").authenticated()
//                        .requestMatchers("/rent_books/**").authenticated()
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider(null,passwordEncoder()))
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
}