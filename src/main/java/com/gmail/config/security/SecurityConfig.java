package com.gmail.config.security;

import com.gmail.config.JavaConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Import(JavaConfig.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;
    private PasswordEncoder passwordEncoder;
    private Environment environment;


    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery(environment.getProperty("users.query"))
                .authoritiesByUsernameQuery(environment.getProperty("authority.query"));
        auth.inMemoryAuthentication().withUser("user").password("1234").roles("ADMIN");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login*", "/registration*").permitAll()
                .antMatchers("/index").hasRole("USER")
                .antMatchers("/editor").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll()
                .defaultSuccessUrl("/index")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling();
    }
}
