//package com.example.application.views.admin;
//
//import com.example.application.data.service.AuthService;
//import com.example.application.views.logout.LogoutView;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.combobox.ComboBox;
//import com.vaadin.flow.component.html.Div;
//import com.vaadin.flow.component.html.H1;
//import com.vaadin.flow.component.html.H2;
//import com.vaadin.flow.component.orderedlayout.FlexComponent;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.PasswordField;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.RouterLink;
//
////@Route("admin") //NON ERA PRESENTE NEL PROGETTO ORIGINALE
//
//
//@PageTitle("Admin | Pubblica amministrazione Repubblica Italiana")
//public class AdminView extends Div {
//
//    public AdminView(AuthService auth){
//        TextField user = new TextField("Username");
//        PasswordField password1 = new PasswordField("Password");
//        PasswordField password2 = new PasswordField("Conferma Password");
//        ComboBox<String> select = new ComboBox<>();
//        select.setPlaceholder("Seleziona regione");
//
//        select.setItems("Abruzzo", "Basilicata", "Calabria", "Campania",
//                "Emilia Romagna", "Friuli-Venezia Giulia", "Lazio", "Liguria", "Lombardia", "Marche", "Molise", "Piemonte",
//                "Puglia", "Sardegna", "Sicilia", "Toscana", "Trentino-Alto Adige", "Umbria", "Val d'Aosta", "Veneto");
//
//
//        addClassName("admin-view");
//        //setSizeFull();
//
//        VerticalLayout output = new VerticalLayout(
//                new H1("Pubblica amministrazione Repubblica Italiana"),
//                new H2("Registra nuovo utente"),
//                user,
//                password1,
//                password2,
//                select,
//                new Button("Registra", event -> {
//                    try {
//
//                        //controlla password1 == password2
//                        //registra utente nel database con questi username, password1 e regione
//                    } catch (Exception e) {
//
//                    }}),
//                new RouterLink("Logout", LogoutView.class)
//        );
//        output.setAlignItems(FlexComponent.Alignment.CENTER);
//        output.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
//
//        add(output);
//    }
//}