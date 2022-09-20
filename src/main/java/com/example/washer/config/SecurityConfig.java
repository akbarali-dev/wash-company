package com.example.washer.config;

import com.example.washer.security.JWTFilter;
import com.example.washer.security.JwtAuthenticationEntryPoint;
import com.example.washer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserService userService;


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    public AuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter();
    }


    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()
                .mvcMatchers("/api/super")
                .hasRole("SUPER_ADMIN")

                .mvcMatchers("/api/admin", "/api/changePassword", "/api/*/getJournals",
                "/api/*/insertJournal/**", "/api/*/insertJournal", "/api/sendMessage"
                )
                .hasAnyRole("ADMIN", "SUPER_ADMIN")

                .mvcMatchers("/api/user")
                .hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")

                .antMatchers( "/api/no", "/api/signIn", "api/signUp")
                .permitAll()
                .anyRequest().authenticated()
//                .antMatchers("/api/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()


//        http.authorizeRequests()
//                .mvcMatchers("/api/book/delete")
//                .hasRole("SUPER_ADMIN")
//                .mvcMatchers("/api/book/add")
//                .hasAnyRole("ADMIN", "SUPER_ADMIN")
//                .mvcMatchers("/api/book/by-id", "/api/book/all")
//                .hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
//                .antMatchers("/api/login")
//                .permitAll()
//                .mvcMatchers("/**")
//                .authenticated()
////                .and()
////                .formLogin()

//
//        http
//                .csrf()
//                .disable()
//
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/login")
//                .permitAll()
//
//                .mvcMatchers("/api/book/delete")
//                .hasRole("SUPER_ADMIN")
//                .mvcMatchers("/api/book/add")
//                .hasAnyRole("ADMIN", "SUPER_ADMIN")
//                .mvcMatchers("/api/book/by-id", "/api/book/all")
//                .hasAnyRole("USER", "ADMIN", "SUPER_ADMIN")
//                .anyRequest().authenticated()

        ;


        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
