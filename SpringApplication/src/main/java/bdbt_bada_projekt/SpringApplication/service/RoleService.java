package bdbt_bada_projekt.SpringApplication.service;

import java.util.ArrayList;
import java.util.List;

public class RoleService {

    private List<String> roles;

    public RoleService() {
        roles = new ArrayList<>();
        roles.add("USER");
        roles.add("ADMIN");
    }

    public List<String> getRoles() {
        return roles;
    }


}
