package bdbt_bada_projekt.SpringApplication.service;

import bdbt_bada_projekt.SpringApplication.dao.ArtistDAO;
import bdbt_bada_projekt.SpringApplication.dao.EmployeeDAO;
import bdbt_bada_projekt.SpringApplication.entity.Artist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistDAO artistDAO;
    private final EmployeeDAO employeeDAO;

    @Transactional
    public void saveArtist(Artist artist) {
        if (!employeeDAO.emailExists(artist.getEmail())) {
            artistDAO.save(artist);
        } else {
            throw new RuntimeException("Email is already in use");
        }
    }

    public Artist findByEmail(String email) {
        int id = employeeDAO.findByEmail(email).getEmployeeId();
        Artist artist = artistDAO.findByEmployeeId(id);

        if (artist == null) {
            throw new EntityNotFoundException("Artist not found with email: " + email);
        }

        return artist;
    }

    public List<Artist> getAllArtists() {
        return artistDAO.findAll();
    }


    public void deleteArtistByEmployeeId(int employeeId) {
        artistDAO.deleteByEmployeeId(employeeId);
    }

    public void updateArtistSkills(int employeeId, String newSkills) {
        Artist artist = findByEmployeeId(employeeId);
        if (artist != null) {
            artist.setSkills(newSkills);
            artistDAO.update(artist);
        }
    }

    public void updateArtistIsMusic(int employeeId, String isMusic) {
        Artist artist = findByEmployeeId(employeeId);
        if (artist != null) {
            artist.setIsMusic(isMusic);
            artistDAO.update(artist);
        }
    }

    public void updateArtistIsSinger(int employeeId, String isSinger) {
        Artist artist = findByEmployeeId(employeeId);
        if (artist != null) {
            artist.setIsSinger(isSinger);
            artistDAO.update(artist);
        }
    }

    public void deleteArtistByEmail(String email) {
        artistDAO.deleteByEmployeeId(employeeDAO.findByEmail(email).getEmployeeId());
    }

    public void deleteArtistById(int employeeId) {
        artistDAO.delete(employeeId);
    }

    public boolean isEmailAlreadyInUse(Artist newArtist) {
        return employeeDAO.emailExists(newArtist.getEmail());
    }

    public Artist findByEmployeeId(int employeeId) {
        Artist artist = artistDAO.findByEmployeeId(employeeId);
        if (artist == null) {
            throw new EntityNotFoundException("Artist not found with employeeId: " + employeeId);
        }
        return artist;
    }

    public void save(Artist artist){
        log.info("Succesfully saved:[{}]", artist.isEmployeeId());
        artistDAO.save(artist);
    }
}
