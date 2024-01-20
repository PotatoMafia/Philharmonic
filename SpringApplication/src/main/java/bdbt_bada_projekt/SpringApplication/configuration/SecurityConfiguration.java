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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
   private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/registration", "/login").permitAll()
                .antMatchers("/resources/static/**").permitAll()
                .antMatchers("/main").authenticated()
                //.antMatchers("/main_admin").hasAuthority("ADMIN")
                //.antMatchers("/edit_users").hasAuthority("ADMIN")
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/user/**").hasAuthority("USER")
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
