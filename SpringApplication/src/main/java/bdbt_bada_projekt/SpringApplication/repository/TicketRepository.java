package bdbt_bada_projekt.SpringApplication.repository;

import bdbt_bada_projekt.SpringApplication.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
