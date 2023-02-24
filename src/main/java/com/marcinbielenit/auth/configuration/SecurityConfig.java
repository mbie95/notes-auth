package com.marcinbielenit.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author marcin
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/registration")
                .and().authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/welcome").authenticated()
                .and().formLogin().defaultSuccessUrl("/welcome");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
    }
}
