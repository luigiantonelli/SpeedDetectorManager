package com.example.application.views.admin;

import com.example.application.data.service.AuthService;
import com.example.application.views.logout.LogoutView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import it.uniroma1.commons.entity.User;
import it.uniroma1.commons.enums.Role;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

//@Route("admin") //NON ERA PRESENTE NEL PROGETTO ORIGINALE


@PageTitle("Admin | Pubblica amministrazione Repubblica Italiana")
public class AdminView extends Div {

    public AdminView(AuthService auth){
        TextField user = new TextField("Username");
        PasswordField password1 = new PasswordField("Password");
        PasswordField password2 = new PasswordField("Conferma Password");
        ComboBox<String> select = new ComboBox<>();
        select.setPlaceholder("Seleziona regione");
        select.setItems("Abruzzo", "Basilicata", "Calabria", "Campania",
                "Emilia Romagna", "Friuli-Venezia Giulia", "Lazio", "Liguria", "Lombardia", "Marche", "Molise", "Piemonte",
                "Puglia", "Sardegna", "Sicilia", "Toscana", "Trentino-Alto Adige", "Umbria", "Val d'Aosta", "Veneto");

        ComboBox<String> selectRegion = new ComboBox<>();
        selectRegion.setPlaceholder("Seleziona ruolo");
        selectRegion.setItems("User","Admin");


        addClassName("admin-view");
        //setSizeFull();

        VerticalLayout output = new VerticalLayout(
                new H1("Pubblica amministrazione Repubblica Italiana"),
                new H2("Registra nuovo utente"),
                user,
                password1,
                password2,
                new HorizontalLayout(select, selectRegion),
                new Button("Registra", event -> {
                    try {
                        if(! password1.getValue().equals(password2.getValue()))
                            Notification.show("Password differenti.");
                        else{


                            auth.register(user.getValue(),password1.getValue(),select.getValue(), Role.USER);
                            Notification.show("Utente "+user.getValue()+" con regione "+selectRegion.getValue()+" creato.");
                            user.setValue("");
                            password1.setValue("");
                            password2.setValue("");
                            select.setValue(null);
                            selectRegion.setValue(null);


                        }



                        //controlla password1 == password2
                        //registra utente nel database con questi username, password1 e regione
                    } catch (Exception e) {

                    }}),
                new RouterLink("Logout", LogoutView.class)
        );
        output.setAlignItems(FlexComponent.Alignment.CENTER);
        output.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        add(output);
    }
}