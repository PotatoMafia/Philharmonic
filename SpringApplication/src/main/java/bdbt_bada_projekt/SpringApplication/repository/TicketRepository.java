package bdbt_bada_projekt.SpringApplication.repository;

import bdbt_bada_projekt.SpringApplication.entity.Ticket;
import bdbt_bada_projekt.SpringApplication.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByTransaction(Transaction transaction);
}
