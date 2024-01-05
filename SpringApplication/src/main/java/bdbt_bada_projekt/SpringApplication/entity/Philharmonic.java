package bdbt_bada_projekt.SpringApplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Philharmonic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int philharmonicId;
    private String name;
    private String address;
    private String owner;
    private Date dateOfCreation;

    // Геттеры и сеттеры

    public int getPhilharmonicId() {
        return philharmonicId;
    }

    public void setPhilharmonicId(int philharmonicId) {
        this.philharmonicId = philharmonicId;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
