package com.kestrel.weddingbookkeeper.common.configuration;

import com.kestrel.weddingbookkeeper.api.auth.utils.AuthTokenProvider;
import com.kestrel.weddingbookkeeper.api.auth.utils.JwtAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthTokenProvider authTokenProvider;

    public SecurityConfiguration(AuthTokenProvider authTokenProvider) {
        this.authTokenProvider = authTokenProvider;
    }

    // TODO: 개발용 시큐리티 라우트 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter(authTokenProvider);

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/api/v1/oauth/**").permitAll()
//                .antMatchers("/", "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors().and()
                .formLogin().disable()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
