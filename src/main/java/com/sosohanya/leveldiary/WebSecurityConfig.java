package com.sosohanya.leveldiary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity  //@EnableWebSecurity : Spring Security의 웹 보안 지원을 활성화하고 Spring MVC 통합을 제공
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//보안 처리할 경로와 처리하지 않을 경로 정의 
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll() //1. h2-console 에서 접근할 수 있도록 설정 
                .anyRequest().authenticated()
                .and()
            .csrf() 
            	.ignoringAntMatchers("/h2-console/**") //2. csrf 설정으로 h2-console 콘솔에서 접속 시도하면 인증화면으로 변경되는 문제 해결 
            	.and()
        	.headers()
        		.frameOptions().sameOrigin() //3. h2-console 콘솔 접속 후 화면 표시 이상 해결 
        		.and()
            .formLogin()
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and();
    }

    @Bean //<-- 이 부분을 빼먹어서 '자격 증명에 실패하였습니다' 메세지를 찾는다고 고생. 주의!!!
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("pwd")
                .roles("USER")
                .build();
        
        System.out.println("password : " + user.getPassword());

        return new InMemoryUserDetailsManager(user);
    }
	
}
