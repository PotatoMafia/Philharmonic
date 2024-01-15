package bdbt_bada_projekt.SpringApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = SecurityConfigurationTest.class)
public class AuthenticationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAuthenticationSuccess() throws Exception {
        mockMvc.perform(formLogin().user("john.doe@example.com").password("mySecretPassword"))
                .andExpect(authenticated())
                .andDo(print());
        System.out.println("Result!");
    }


    @Test
    public void testAuthenticationFailure() throws Exception {
        mockMvc.perform(formLogin().user("Nastya").password("wrongPassword"))
                .andExpect(unauthenticated());
    }
}
