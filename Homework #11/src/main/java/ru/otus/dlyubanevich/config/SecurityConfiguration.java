package ru.otus.dlyubanevich.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.dlyubanevich.service.LibraryUserDetailService;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final LibraryUserDetailService libraryUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
           .csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/**").authenticated()
           .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/**").authenticated()
           .and()
                .authorizeRequests().antMatchers(HttpMethod.DELETE, "/**").authenticated()
           .and()
           .formLogin().loginProcessingUrl("/login").usernameParameter("login").passwordParameter("password");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(libraryUserDetailService);
    }

}
