package bdbt_bada_projekt.SpringApplication;

import bdbt_bada_projekt.SpringApplication.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class AppController implements WebMvcConfigurer {

    @Autowired
    private CustomerDAO dao;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/login").setViewName("login");

        registry.addViewController("/main_admin").setViewName("admin/main_admin");
        registry.addViewController("/edit_users").setViewName("admin/edit_users");
        registry.addViewController("/main_user").setViewName("main_user");

        registry.addViewController("/events").setViewName("events");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/registration").setViewName("registration");

    }

//    @RequestMapping(value = {"/main_admin"})
//    public String showAdminPage(Model model) {
//        if()
//        return "admin/main_admin";
//    }
//
//    @RequestMapping(value = {"/main_user"})
//    public String showUserPage(Model model) {
//        return "user/main_user";
//    }

    @RequestMapping(value = {"/events"})
    public String showEventsPage(Model model) {
        return "events";
    }

    @RequestMapping(value = {"/about"})
    public String showAboutPage(Model model) {
        return "about";
    }



}