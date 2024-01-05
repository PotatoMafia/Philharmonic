package bdbt_bada_projekt.SpringApplication.entity;

import javax.persistence.Entity;

@Entity
public class Artist extends Employee {
    private String skills;
    private boolean isMusic;
    private boolean isSinger;

    // Геттеры и сеттеры

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public boolean isMusic() {
        return isMusic;
    }

    public void setMusic(boolean music) {
        isMusic = music;
    }

    public boolean isSinger() {
        return isSinger;
    }

    public void setSinger(boolean singer) {
        isSinger = singer;
    }
}
