package bdbt_bada_projekt.SpringApplication.entity;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
@Table(name = "\"Artists\"")
public class Artist extends Employee {
    @Id
    @Column(name = "\"Employee_id\"")
    private int employeeId;
    @Column(name = "\"Skills\"")
    private String skills;
    @Column(name = "\"Is_music\"")
    private String isMusic;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    private String name;
    private String surname;
    @Override
    public int getEmployeeId() {
        return employeeId;
    }

    public String getIsMusic() {
        return isMusic;
    }

    public void setIsMusic(String isMusic) {
        this.isMusic = isMusic;
    }

    public String getIsSinger() {
        return isSinger;
    }

    public void setIsSinger(String isSinger) {
        this.isSinger = isSinger;
    }

    @Column(name = "\"Is_singer\"")
    private String isSinger;

    public int isEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }




    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }


}
