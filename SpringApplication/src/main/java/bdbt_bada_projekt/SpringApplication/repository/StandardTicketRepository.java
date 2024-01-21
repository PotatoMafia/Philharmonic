package bdbt_bada_projekt.SpringApplication.repository;

        import bdbt_bada_projekt.SpringApplication.entity.ticketType.StandardTicket;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardTicketRepository extends JpaRepository<StandardTicket, Long> {
}
