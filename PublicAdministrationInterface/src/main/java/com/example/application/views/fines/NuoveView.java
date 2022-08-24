package com.example.application.views.fines;

//import com.example.application.data.entity.Fine;
//import com.example.application.data.entity.User;
import com.example.application.data.service.AuthService;
//import com.example.application.data.service.FineRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.server.VaadinSession;
import it.uniroma1.commons.entity.Fine;
import it.uniroma1.commons.repository.FineRepository;
import it.uniroma1.commons.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

//@Route(value = "multe/gestite", layout = FinesView.class)
public class NuoveView extends VerticalLayout {
    @Autowired
    private FineRepository fineRepository;
    Grid<Fine> grid = new Grid<>(Fine.class);
    TextField filterText = new TextField();

    public NuoveView() {
        addClassName("nuove-view");
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
        //grid.setItems(fineRepository.getFines());
        grid.setItems(fineRepository.findAll());

    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        //grid.setColumns("Codice_Multa");
        grid.addColumn(fine -> fine.getId()).setHeader("Codice");
        grid.addColumn(fine -> fine.getSpeedCamera()).setHeader("Autovelox");
        //grid.addColumn(fine -> fine.link_pdf).setHeader("PDF");

        //grid.addColumn(fine -> new Button("Invia")).setHeader("Invia Multa");

        //grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        //grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");*/
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filtra per autovelox...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);


        Button updateButton = new Button("Aggiorna", event -> {
            //grid.setItems(fineRepository.getFines().stream().toList());
            grid.setItems(fineRepository.findAll());

        });
        HorizontalLayout toolbar = new HorizontalLayout(filterText, updateButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}
