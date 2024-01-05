package bdbt_bada_projekt.SpringApplication.repository;

        import bdbt_bada_projekt.SpringApplication.entity.ticketType.StandartTicket;
        import bdbt_bada_projekt.SpringApplication.entity.ticketType.VIPTicket;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface StandartTicketRepository extends JpaRepository<StandartTicket, Long> {
}
