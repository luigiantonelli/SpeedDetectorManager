package com.example.application.data.service;

import com.example.application.views.admin.AdminView;
import com.example.application.views.admin.RegistrationView;
import com.example.application.views.admin.UserInfoView;

import com.example.application.views.admin.UsersView;
import com.example.application.views.fines.FinesView;
import com.example.application.views.fines.GestiteView;
import com.example.application.views.fines.InfoFineView;
import com.example.application.views.fines.NuoveView;
import com.example.application.views.logout.LogoutView;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import it.uniroma1.commons.entity.User;
import it.uniroma1.commons.enums.Role;
import it.uniroma1.commons.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public static String registrationRoute = "admin/registrazione";
    public static String usersRoute = "admin/utenti";
    public static String userInfoRoute = "admin/utenti/info";
    //public static String userFinesRoute = "admin/utenti/info/multa";
    public static String userInfoFineRoute = "admin/utenti/info/multa";

    public static String gestiteRoute = "multe/gestite";
    public static String nuoveRoute = "multe/nuove";
    public static String infoFineRoute = "multe/info";

    private HashSet<String> FinesLayoutRoutes=new HashSet<>();
    private HashSet<String> AdminLayoutRoutes=new HashSet<>();
    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {

    }

    public class AuthException extends Exception {

    }



    public void authenticate(String username, String password, String region) throws AuthException {
        AdminLayoutRoutes.add(registrationRoute);
        AdminLayoutRoutes.add(usersRoute);
        AdminLayoutRoutes.add(userInfoFineRoute);
        //AdminLayoutRoutes.add(userFinesRoute);


        FinesLayoutRoutes.add(infoFineRoute);
        FinesLayoutRoutes.add(nuoveRoute);
        FinesLayoutRoutes.add(gestiteRoute);




        //User user = userRepository.getByUsername(username);
        //prendi utente dal database
        Optional<User> optionalUser = userRepository.findById(username);
        User user = optionalUser.isPresent()?optionalUser.get():null;
        if (user != null && user.checkPassword(password) && user.checkRegion(region)/*user.isActive()*/) {

            VaadinSession.getCurrent().setAttribute(User.class, user);  // FORSE DA TOGLIERE
            createRoutes(user.getRole());
            if(user.getRole().equals(Role.ADMIN)){
                UI.getCurrent().navigate(AuthService.registrationRoute);
            }
            else{
                UI.getCurrent().navigate(AuthService.nuoveRoute);
            }
        } else {
            throw new AuthException();
        }
    }

    /*private void createRoutes(Role role) {
        getAuthorizedRoutes(role).stream()
                .forEach(route ->{
                        if(route.name.equals("Logout")){
                            RouteConfiguration.forSessionScope().setRoute(
                                    route.route, route.view, MainView.class);}

                        else if(route.name.equals("admin/registrazione") || route.name.equals("admin/nuove")){
                        RouteConfiguration.forSessionScope().setRoute(
                                route.route, route.view, MainView.class);}
                        else{
                            RouteConfiguration.forSessionScope().setRoute(
                                    route.route, route.view, FinesView.class);
                        }
                        }
                        );

    }*/
    private void createRoutes(Role role) {
        getAuthorizedRoutes(role).stream()
                .forEach(route ->{
                            if(AdminLayoutRoutes.contains(route.route)){
                                RouteConfiguration.forSessionScope().setRoute(
                                        route.route, route.view, AdminView.class);}

                            else if(FinesLayoutRoutes.contains(route.route)){
                                RouteConfiguration.forSessionScope().setRoute(
                                        route.route, route.view, FinesView.class);}
                            else{
                                RouteConfiguration.forSessionScope().setRoute(
                                        route.route, route.view, MainView.class);
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
            routes.add(new AuthorizedRoute(nuoveRoute, "MulteNuove", NuoveView.class));
            routes.add(new AuthorizedRoute(gestiteRoute, "MulteGestite", GestiteView.class));
            routes.add(new AuthorizedRoute(infoFineRoute, "InformazioniMulta", InfoFineView.class));

        }
        else if (role.equals(Role.ADMIN)) {

            routes.add(new AuthorizedRoute(registrationRoute, "Registration", RegistrationView.class));
            routes.add(new AuthorizedRoute(usersRoute, "Utenti", UsersView.class));
            routes.add(new AuthorizedRoute(userInfoRoute,"Utenti", UserInfoView.class));
            routes.add(new AuthorizedRoute(userInfoFineRoute,"Utenti", InfoFineView.class));
        }
        return routes;
    }

   public void register(String username, String password, String region, Role role) {

       User newUser = new User();
       boolean useLetters = true;
       boolean useNumbers = false;
       int length = 10;
       String passwordSalt = RandomStringUtils.random(length, useLetters, useNumbers);

       newUser.setUsername(username);
       newUser.setName(username); //DA MODIFICARE
       newUser.setSurname(username); //DA MODIFICARE
       newUser.setPasswordSalt(passwordSalt);
       newUser.setPasswordHash(DigestUtils.sha1Hex(password + passwordSalt));
       newUser.setRegion(region);
       //valutare se mettere campo per il ruolo

       newUser.setRole(Role.USER);
//       newUser.setActive(false); // DA MODIFICARE
//       newUser.setActivationCode(username); //DA MODIFICARE

       User creator = VaadinSession.getCurrent().getAttribute(User.class);
       newUser.setCreator(creator);

       //salvataggio dell'utente nel database
       userRepository.save(newUser);
   }

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
