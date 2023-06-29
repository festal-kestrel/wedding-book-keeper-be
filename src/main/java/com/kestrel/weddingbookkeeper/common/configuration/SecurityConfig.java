package com.kestrel.weddingbookkeeper.common.configuration;

import com.kestrel.weddingbookkeeper.api.auth.utils.AuthTokenProvider;
import com.kestrel.weddingbookkeeper.api.auth.utils.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { // SecurityFilterChain

    private final AuthTokenProvider authTokenProvider;

    public SecurityConfig(AuthTokenProvider authTokenProvider) {
        this.authTokenProvider = authTokenProvider;
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/v1/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter(authTokenProvider);

        http
                .csrf().disable()
                .authorizeHttpRequests()

//                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/api/v1/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and() // 해당 요청을 인증된 사용자만 사용 가능
//                .headers()
//                .frameOptions()
//                .sameOrigin().and()
                .cors().and()
                .formLogin().disable()
                //.exceptionHandling() // TODO 예외 처리 필요한지 체크할 것
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


    }
}
