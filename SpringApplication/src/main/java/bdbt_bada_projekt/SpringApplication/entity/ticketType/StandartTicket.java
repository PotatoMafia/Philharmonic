package bdbt_bada_projekt.SpringApplication.entity.ticketType;

import bdbt_bada_projekt.SpringApplication.entity.Ticket;

import javax.persistence.Entity;

@Entity
public class StandartTicket extends Ticket {
    private boolean standardAccess;
    private boolean standardParking;


    public boolean isStandardAccess() {
        return standardAccess;
    }

    public void setStandardAccess(boolean standardAccess) {
        this.standardAccess = standardAccess;
    }

    public boolean isStandardParking() {
        return standardParking;
    }

    public void setStandardParking(boolean standardParking) {
        this.standardParking = standardParking;
    }
}
