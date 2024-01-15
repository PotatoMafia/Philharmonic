package bdbt_bada_projekt.SpringApplication.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orchestra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orchestraId;
    private String name;

    // Геттеры и сеттеры

    public int getOrchestraId() {
        return orchestraId;
    }

    public void setOrchestraId(int orchestraId) {
        this.orchestraId = orchestraId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Role implements GrantedAuthority {

        private String roleName;

        public Role(String roleName) {
            this.roleName = roleName;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        @Override
        public String getAuthority() {
            return roleName;
        }
    }
}
