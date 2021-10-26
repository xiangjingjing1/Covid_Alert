package com.example.Covid_Alert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class CovidAlertSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    // ... configure() method with security definition

    protected void configure(final HttpSecurity httpSecurity) throws  Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/login*").permitAll()
                .antMatchers("/static/css/**","/static/js/**",
                        "/images/**").permitAll()
                .antMatchers("/index*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .failureUrl("/login?error=true").permitAll()
                .defaultSuccessUrl("/",true)
                .and()
                .rememberMe()
                .key("cleSuperSecrete")
                .tokenRepository(tokenRepository());
    }
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("bob").password(passwordEncoder()
//                .encode("password_de_bob")).roles("USER");
//    }
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(passwordEncoder()).dataSource(dataSource)
                .withUser("admin").password(passwordEncoder().encode("adminadmin")).disabled(false).roles("USER","ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl token = new JdbcTokenRepositoryImpl();
        token.setDataSource(dataSource);
        return token;
    }
}
