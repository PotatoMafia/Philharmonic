package bdbt_bada_projekt.SpringApplication.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Slf4j
@Entity
@Table(name = "\"Employees\"")
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column(name = "\"Name\"")
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "\"Surname\"")
    @NotNull
    @NotEmpty
    private String surname;

    @Column(name = "\"Gender\"")
    private String gender;

    @Column(name = "\"Personal_number\"")
    private String personalNumber;

    @Column(name = "\"Email\"")
    @NotNull
    @NotEmpty
    @Email
    private String email;

    @Column(name = "\"Employment_date\"")
    private LocalDateTime employmentDate;

    @Column(name = "\"Job_title\"")
    private String jobTitle;

    public int getPhilharmonic_id() {
        return philharmonic_id;
    }

    public void setPhilharmonic_id(int philharmonic_id) {
        this.philharmonic_id = philharmonic_id;
    }

    @Column(name = "\"Philharmonic_id\"")
    private int philharmonic_id = 1;


    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Column(name = "\"Street\"")
    private String street;

    @Column(name = "\"Local_number\"")
    private String localNumber;

    @Column(name = "\"PostCode\"")
    private String postCode;

    @Column(name = "\"Pay_per_hour\"")
    private double payPerHour;

    @Column(name = "\"Phone_number\"", length = 15)
    private String phoneNumber;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public LocalDateTime getEmploymentDate() {
        return employmentDate;
    }



    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(double payPerHour) {
        this.payPerHour = payPerHour;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;  // You might want to implement this if needed
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
