package bdbt_bada_projekt.SpringApplication.service;

import bdbt_bada_projekt.SpringApplication.dao.CustomerDAO;
import bdbt_bada_projekt.SpringApplication.entity.Customer;
import bdbt_bada_projekt.SpringApplication.entity.Ticket;
import bdbt_bada_projekt.SpringApplication.entity.Transaction;
import bdbt_bada_projekt.SpringApplication.repository.CustomerRepository;
import bdbt_bada_projekt.SpringApplication.repository.TicketRepository;
import bdbt_bada_projekt.SpringApplication.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {

    private final CustomerDAO customerDAO;
    private final PasswordEncoder passwordEncoder;
    private CustomerRepository customerRepository;
    private TicketRepository ticketRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerDAO customerDAO, PasswordEncoder passwordEncoder, TransactionRepository transactionRepository, TicketRepository ticketRepository) {
        this.customerRepository = customerRepository;
        this.customerDAO = customerDAO;
        this.passwordEncoder = passwordEncoder;
        this.ticketRepository= ticketRepository;
        this.transactionRepository = transactionRepository;
    }



//    public void saveCustomer(Customer customer) {
//        customerDAO.save(customer);
//    }

    public Customer findCustomerByMail(Customer customer) {
        return customerDAO.findByEmail(customer.getEmail());
    }

    public Boolean isEmailAlreadyInUse(Customer customer) {
        return customerDAO.emailExists(customer.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerDAO.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
    }
//    @Transactional
//    public void saveUser(Customer customer) {
//        customerDAO.save(customer);
//    }

    @Transactional
    public void saveCustomer(Customer customer) {
        if (!isEmailAlreadyInUse(customer)) {
            String encodedPassword = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(encodedPassword);
            customer.setRole("USER");
            customerDAO.save(customer);
        } else {
            throw new RuntimeException("Email is already in use");
        }
    }

    public boolean checkPassword(String email, String enteredPassword) {
        Customer customer = customerDAO.findByEmail(email);
        if (customer != null) {
            String hashedPassword = customer.getPassword();
            return passwordEncoder.matches(enteredPassword, hashedPassword);
        }
        return false;
    }


    public Customer findByEmail(String email) {
        Customer customer = customerDAO.findByEmail(email);

        if (customer == null) {
            throw new EntityNotFoundException("Customer not found with email: " + email);
        }

        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }


    public boolean isAuthenticated(String providedPassword, String actualPassword) {

        providedPassword = providedPassword.trim();

        actualPassword = actualPassword.trim();

        return actualPassword.equals(providedPassword);
    }

    public String login(String email, String password) {
        Customer customer = customerDAO.findByEmail(email);
        log.info("Customer was found by email [{}]", email);
        log.info("Customer role: [{}]", customer.getRole());
        if (customer != null && checkPassword(email, password)) {
            return customer.getRole();
        }
        return null;
    }


    public void customerUpdateByRole(String email, String role){
        Customer customer = findByEmail(email);
        if (customer != null) {
            customer.setRole(role);
            customerDAO.update(customer);
        }
    }

    public void customerUpdateByName(String email, String name){
        Customer customer = findByEmail(email);
        if (customer != null) {
            customer.setName(name);
            customerDAO.update(customer);
        }
    }


    public void customerUpdateBySurname(String email, String surname){
        Customer customer = findByEmail(email);
        if (customer != null) {
            customer.setSurname(surname);
            customerDAO.update(customer);
        }
    }

    public void customerUpdate(Customer customer){

        customerDAO.update(customer);
    }

    public void deleteCustomerByEmail(String email) {
        customerDAO.deleteByEmail(email);
    }

    public List<Ticket> getAllTicketsForCustomer(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElse(null);

        if (customer != null) {
            List<Transaction> transactions = transactionRepository.findByCustomer(customer);
            List<Ticket> allTickets = new ArrayList<>();

            for (Transaction transaction : transactions) {
                List<Ticket> tickets = ticketRepository.findByTransaction(transaction);
                allTickets.addAll(tickets);
            }

            return allTickets;
        } else {
            // Handle the case when customer is not found
            return Collections.emptyList();
        }
    }


}
