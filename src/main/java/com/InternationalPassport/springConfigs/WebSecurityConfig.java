package com.InternationalPassport.springConfigs;

import com.InternationalPassport.security.UserDetailsServiceImpl;
import com.InternationalPassport.security.afterSignIn.MyAuthenticationSuccessHandler;
import com.InternationalPassport.security.deniedHandler.MyAccessDeniedHandler;
import com.InternationalPassport.security.helper.SecurityUserHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
//@EnableWebSecurity(debug = true)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MyAuthenticationSuccessHandler();
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }

    @Bean
    public SecurityUserHelper getUserHelper () {
        return new SecurityUserHelper();
    }

//    @Bean
//    public ExceptionTranslationFilter exceptionTranslationFilter() {
//        ExceptionTranslationFilter etf = new ExceptionTranslationFilter(authenticationEntryPoint());
//        etf.setAccessDeniedHandler(accessDeniedHandler());
//        return etf;
//    }
//
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        AuthenticationEntryPoint authenticationEntryPoint = new LoginUrlAuthenticationEntryPoint("/signin");
//        return authenticationEntryPoint;
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider () {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder amb) throws Exception {
        amb.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.debug(true);
        web.ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/resources/bootstrapComponent/**")
                .antMatchers("/resources/js/**")
                .antMatchers("/resources/jsFiles/**")
                .antMatchers("/resources/css/**")
                .antMatchers("/resources/patternViews/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers()
                .defaultsDisabled()
                .contentTypeOptions().and()
                .frameOptions().and()
                .xssProtection()
                .block(true).and()
                .contentSecurityPolicy ( "script-src 'self'").and()
                .cacheControl();
        http.authorizeRequests()
                .antMatchers("/loadPage").hasAnyRole("CEO", "Manager", "User")
                .antMatchers("/home").hasAnyRole("CEO", "Manager", "User")
                .antMatchers("/control", "/control/customer", "/control/customer/*", "/control/**").hasAnyRole("CEO", "Manager")
                .antMatchers("/ceo", "/ceo/customer", "/ceo/customer/*",  "/ceo**").hasAnyRole("CEO")
                .antMatchers("/error").hasAnyRole("CEO", "Manager", "User")
                .antMatchers("/main", "/signin", "/signUp").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/signin")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/signin")
//                .defaultSuccessUrl("/processSignIn")
//                .successForwardUrl("/processSignIn")
                .successHandler(myAuthenticationSuccessHandler())
                .and()
                .logout()
//                .logout().deleteCookies("JSESSIONID")
                .logoutUrl("/signout")
                .logoutSuccessUrl("/signin")
                .and()
//                    .exceptionHandling().accessDeniedPage("/error");
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
                /*.and()
                .sessionManagement()
                .invalidSessionUrl("/signin")
                .maximumSessions(1);*/
    }
}
