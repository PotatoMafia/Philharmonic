package bdbt_bada_projekt.SpringApplication.repository;

import bdbt_bada_projekt.SpringApplication.entity.Event;
import bdbt_bada_projekt.SpringApplication.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventByTickets(Ticket ticket);
}
