package bdbt_bada_projekt.SpringApplication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.jdbc.config.annotation.web.http.JdbcHttpSessionConfiguration;

@Configuration
@EnableJdbcHttpSession
public class SessionConfiguration {
    @Primary
    @Bean
    public JdbcHttpSessionConfiguration jdbcHttpSessionConfiguration() {
        return new JdbcHttpSessionConfiguration();
    }
}
