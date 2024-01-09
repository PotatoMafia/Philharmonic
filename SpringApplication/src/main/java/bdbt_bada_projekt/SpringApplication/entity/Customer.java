package bdbt_bada_projekt.SpringApplication.entity;

import javax.persistence.*;

@Entity
@Table(name = "C##AKUGACZ.\"Customer\"")
public class Customer {
    @Id
    @Column(name = "C##AKUGACZ.\"Customer\".\"Customer_id\"")
    private int customerId;

    @Column(name = "C##AKUGACZ.\"Customer\".\"Name\"")
    private String name;

    @Column(name = "C##AKUGACZ.\"Customer\".\"Surname\"")
    private String surname;

    @Column(name = "C##AKUGACZ.\"Customer\".\"Phone_number\"", length = 15)
    private String phoneNumber;

    @Column(name = "C##AKUGACZ.\"Customer\".\"Address\"")
    private String address;

    @Column(name = "C##AKUGACZ.\"Customer\".\"PersonalNumber\"")
    private String personalNumber;

    @Column(name = "C##AKUGACZ.\"Customer\".\"Email\"")
    private String email;

    public Customer(int id, String name, String surname, String phoneNumber, String address, String personalNumber, String email) {
        this.customerId = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.personalNumber = personalNumber;
        this.email = email;
    }

    public Customer() {
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
