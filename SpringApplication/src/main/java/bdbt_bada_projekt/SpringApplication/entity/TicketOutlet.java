package bdbt_bada_projekt.SpringApplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TicketOutlet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketOutletId;
    private String name;
    private String address;
    private String workingHours;
    private String phoneNumber;
    private String email;

    // Геттеры и сеттеры

    public int getTicketOutletId() {
        return ticketOutletId;
    }

    public void setTicketOutletId(int ticketOutletId) {
        this.ticketOutletId = ticketOutletId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
