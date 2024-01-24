package bdbt_bada_projekt.SpringApplication.configuration;

import bdbt_bada_projekt.SpringApplication.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
private HttpSession session;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/registration", "/login").permitAll()
                .antMatchers("/resources/static/**").permitAll()
                //.antMatchers("/main").authenticated()
                .antMatchers("**/edit_event").access("hasAuthority('ADMIN') and #session.getAttribute('registered')")
                .antMatchers("**/edit_users").access("hasAuthority('ADMIN') and #session.getAttribute('registered')")
                .antMatchers("**/edit_employee").access("hasAuthority('ADMIN') and #session.getAttribute('registered')")
                .antMatchers("**/main_admin").access("hasAuthority('ADMIN') and #session.getAttribute('registered')")
                //.antMatchers("**/admin/**").hasAuthority("ADMIN")
                .antMatchers("/user/main_user").access("hasAuthority('USER') and #session.getAttribute('registered')")
                .antMatchers("/registration").not().fullyAuthenticated();
//                .and()
//                .logout()
//                .logoutUrl("/logout").logoutSuccessUrl("/logout").permitAll();

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
