package com.example.application.views.admin;

//import com.example.application.data.entity.Fine;
//import com.example.application.data.entity.User;
import com.example.application.data.service.AuthService;
//import com.example.application.data.service.FineRepository;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import it.uniroma1.commons.entity.Fine;
import it.uniroma1.commons.entity.User;
import it.uniroma1.commons.repository.FineRepository;
import it.uniroma1.commons.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


//import org.springframework.beans.factory.annotation.Autowired;

//@Route(value = "multe/gestite", layout = FinesView.class)
//@Service



public class UsersView extends VerticalLayout {
    // @Autowired
    private final UserRepository userRepository;
    Grid<User> grid = new Grid<>(User.class);
    TextField filterText = new TextField();

    public UsersView(UserRepository userRep) {
        userRepository=userRep;

        addClassName("nuove-view");
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);

        this.refreshGrid();
    }


    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.removeAllColumns();

        grid.addColumn(user -> user.getUsername()).setHeader("Username");
        grid.addColumn(user -> user.getName()+" "+user.getUsername()).setHeader("Impiegato");
        grid.addColumn(user -> user.getRegion()).setHeader("Regione");





        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }




    public void refreshGrid(String filter){
        if(filter.equals(""))
            grid.setItems(userRepository.findAll());

        //grid.setItems(fineRepository.findAllManagedFines(VaadinSession.getCurrent().getAttribute(User.class).getRegion()));
        else
            grid.setItems(userRepository.findFilterAll(filter));
    }
    public void refreshGrid(){
        refreshGrid(filterText.getValue());
    }
    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filtra per username...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);


        Button updateButton = new Button("Aggiorna", event -> refreshGrid());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, updateButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}
