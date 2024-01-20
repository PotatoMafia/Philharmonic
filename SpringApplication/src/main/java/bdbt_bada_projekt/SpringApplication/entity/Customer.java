package bdbt_bada_projekt.SpringApplication.entity;

import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Slf4j
    @Entity
    @Table(name = "\"Customers\"")
    public class Customer implements UserDetails {

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions;

        //private PasswordEncoder passwordEncoder;
        @Id
        @Column(name = "\"Customer_id\"")
        private int customerId;
        @Column(name = "\"Name\"")
        @NotNull
        @NotEmpty
        private String name;
        @Column(name = "\"Surname\"")
        @NotNull
        @NotEmpty
        private String surname;
        @Column(name = "\"Phone_number\"", length = 15)
        private String phoneNumber;
        @Column(name = "\"Personal_number\"")
        private String personalNumber;
        @Column(name = "\"Email\"")
        @NotNull
        @NotEmpty
        @Email
        private String email;
        @Column(name = "\"Street\"")
        private String street;
        @Column(name = "\"Local_number\"")
        private String localNumber;
        @Column(name = "\"Postcode\"")
        private String postcode;
        @Column(name = "\"Philharmonic_id\"")
        private int philharmonic_id = 1;
        @Column(name = "\"Password\"")
        @NotNull
        @NotEmpty
        @Size(min = 6, message = "Password must be at least 6 characters")
        private String password;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        @Column(name = "\"Role\"")
        private String role;


        public Customer() {
        }

        public Customer(int customerId, String name, String surname, String phoneNumber, String personalNumber, String email, String street, String localNumber, String postcode, String password) {
            this.customerId = customerId;
            this.name = name;
            this.surname = surname;
            this.phoneNumber = phoneNumber;
            this.personalNumber = personalNumber;
            this.email = email;
            this.street = street;
            this.localNumber = localNumber;
            this.postcode = postcode;
            this.password = password;
        }

        public Customer(int customerId, String name, String surname, String phoneNumber, String personalNumber, String email, String street, String localNumber, String postcode) {
            this.customerId = customerId;
            this.name = name;
            this.surname = surname;
            this.phoneNumber = phoneNumber;
            this.personalNumber = personalNumber;
            this.email = email;
            this.street = street;
            this.localNumber = localNumber;
            this.postcode = postcode;
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

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getLocalNumber() {
            return localNumber;
        }

        public void setLocalNumber(String localNumber) {
            this.localNumber = localNumber;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public int getPhilharmonic_id() {
            return philharmonic_id;
        }

        public void setPhilharmonic_id(int philharmonic_id) {
            this.philharmonic_id = philharmonic_id;
        }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "customerId=" + customerId +
                    ", name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", personalNumber='" + personalNumber + '\'' +
                    ", email='" + email + '\'' +
                    ", street='" + street + '\'' +
                    ", localNumber='" + localNumber + '\'' +
                    ", postcode='" + postcode + '\'' +
                    ", philharmonic_id=" + philharmonic_id +
                    ", password='" + password + '\'' +
                    '}';
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

