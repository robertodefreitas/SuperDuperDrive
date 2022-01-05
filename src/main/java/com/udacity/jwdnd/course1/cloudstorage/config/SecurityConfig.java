package com.udacity.jwdnd.course1.cloudstorage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import com.udacity.jwdnd.course1.cloudstorage.constants.Path;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;

// Other example of spring security + thymeleaf
// https://mkyong.com/spring-boot/spring-boot-spring-security-thymeleaf-example/
// https://huongdanjava.com/custom-login-page-using-bootstrap-and-thymeleaf-in-spring-security.html

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(Path.SIGNUP, "/css/**", "/js/**", "/h2-console/**", "/static/favicon.ico")
                .permitAll()
                .anyRequest()
                .authenticated();

        httpSecurity
                .formLogin()
                .loginPage(Path.LOGIN)
                .permitAll();

        httpSecurity
                .formLogin()
                .defaultSuccessUrl(Path.HOME, true);

        httpSecurity
                .logout();

        // important to use the h2-console
        // http://localhost:8088/h2-console
        // SOURCE: https://springframework.guru/using-the-h2-database-console-in-spring-boot-with-spring-security/
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

        /**
         * i added this to use the h2 console
         * robertodefreitas
         * https://stackoverflow.com/questions/43794721/spring-boot-h2-console-throws-403-with-spring-security-1-5-2
         */
        /*
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/h2/**");
        }
         */
    }
}
