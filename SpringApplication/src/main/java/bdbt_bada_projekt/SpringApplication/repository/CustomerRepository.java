package bdbt_bada_projekt.SpringApplication.repository;

import bdbt_bada_projekt.SpringApplication.entity.Customer;
import bdbt_bada_projekt.SpringApplication.entity.Ticket;
import bdbt_bada_projekt.SpringApplication.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);

}