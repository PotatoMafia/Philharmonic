package bdbt_bada_projekt.SpringApplication.service;

import bdbt_bada_projekt.SpringApplication.dao.CustomerDAO;
import bdbt_bada_projekt.SpringApplication.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerDAO.findByEmail(email);

        if (customer == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

            String role = customer.getRole();
        return User.withUsername(customer.getEmail())
                .password(customer.getPassword())
                .roles(role)
                .build();
    }
}
