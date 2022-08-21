package com.example.application.data.service;

import com.example.application.data.entity.Role;
import com.example.application.data.entity.User;
import com.example.application.views.admin.AdminView;
import com.example.application.views.fines.FinesView;
import com.example.application.views.fines.GestiteView;
import com.example.application.views.fines.NuoveView;
import com.example.application.views.logout.LogoutView;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {

    }

    public class AuthException extends Exception {

    }

    //private final UserRepository userRepository;
    // private final MailSender mailSender;

    /*public AuthService(UserRepository userRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }*/

    public void authenticate(String username, String password, String region) throws AuthException {
        //User user = userRepository.getByUsername(username);
        //prendi utente dal database
        User user = UserRepository.getByUsername(username);
        if (user != null && user.checkPassword(password) && user.checkRegion(region)/*user.isActive()*/) {
            VaadinSession.getCurrent().setAttribute(User.class, user);  // FORSE DA TOGLIERE
            createRoutes(user.getRole());
            if(user.getRole().equals(Role.ADMIN)){
                UI.getCurrent().navigate("admin");
            }
            else{
                UI.getCurrent().navigate("multe/nuove");
            }
        } else {
            throw new AuthException();
        }
    }

    private void createRoutes(Role role) {
        getAuthorizedRoutes(role).stream()
                .forEach(route ->{
                        if( route.name.equals("Admin") || route.name.equals("Logout")){
                        RouteConfiguration.forSessionScope().setRoute(
                                route.route, route.view, MainView.class);}
                        else{
                            RouteConfiguration.forSessionScope().setRoute(
                                    route.route, route.view, FinesView.class);
                        }
                        }
                        );

    }

    public List<AuthorizedRoute> getAuthorizedRoutes(Role role) {
        ArrayList<AuthorizedRoute> routes = new ArrayList<>();
        routes.add(new AuthorizedRoute("logout", "Logout", LogoutView.class));
        if (role.equals(Role.USER)) {
            //routes.add(new AuthorizedRoute("login", "Login", LoginView.class));
            //routes.add(new AuthorizedRoute("multe", "Multe", FinesView.class));
            routes.add(new AuthorizedRoute("multe/nuove", "MulteNuove", NuoveView.class));
            routes.add(new AuthorizedRoute("multe/gestite", "MulteGestite", GestiteView.class));

        }
        else if (role.equals(Role.ADMIN)) {
            //routes.add(new AuthorizedRoute("login", "Login", LoginView.class));
            //routes.add(new AuthorizedRoute("multe", "Multe", FinesView.class));
            /*routes.add(new AuthorizedRoute("multe", "Multe", FinesView.class));
            routes.add(new AuthorizedRoute("multe/nuove", "MulteNuove", NuoveView.class));
            routes.add(new AuthorizedRoute("multe/gestite", "MulteGestite", GestiteView.class));*/
            routes.add(new AuthorizedRoute("admin", "Admin", AdminView.class));
        }
        return routes;
    }

   /* public void register(String email, String password) {
        User user = userRepository.save(new User(email, password, Role.USER));
        String text = "http://localhost:8080/activate?code=" + user.getActivationCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@example.com");
        message.setSubject("Confirmation email");
        message.setText(text);
        message.setTo(email);
        mailSender.send(message);
    }*/

    /*public void activate(String activationCode) throws AuthException {
        //User user = userRepository.getByActivationCode(activationCode)
        if (user != null) {
            user.setActive(true);
            userRepository.save(user);
        } else {
            throw new AuthException();
        }
    }*/

}
