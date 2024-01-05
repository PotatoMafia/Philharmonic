package bdbt_bada_projekt.SpringApplication.entity.ticketType;

import bdbt_bada_projekt.SpringApplication.entity.Ticket;

import javax.persistence.Entity;

@Entity
public class VIPTicket extends Ticket {
    private String vipService;
    private boolean vipAccess;
    private boolean vipParking;

    // Геттеры и сеттеры

    public String getVipService() {
        return vipService;
    }

    public void setVipService(String vipService) {
        this.vipService = vipService;
    }

    public boolean isVipAccess() {
        return vipAccess;
    }

    public void setVipAccess(boolean vipAccess) {
        this.vipAccess = vipAccess;
    }

    public boolean isVipParking() {
        return vipParking;
    }

    public void setVipParking(boolean vipParking) {
        this.vipParking = vipParking;
    }
}
