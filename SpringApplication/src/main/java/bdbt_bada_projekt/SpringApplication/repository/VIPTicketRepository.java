package bdbt_bada_projekt.SpringApplication.repository;

import bdbt_bada_projekt.SpringApplication.entity.ticketType.VIPTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VIPTicketRepository extends JpaRepository<VIPTicket, Long> {
}

