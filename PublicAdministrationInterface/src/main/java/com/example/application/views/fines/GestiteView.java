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
import it.uniroma1.commons.entity.User;
import it.uniroma1.commons.repository.FineRepository;
import it.uniroma1.commons.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.beans.factory.annotation.Autowired;

//@Route(value = "multe/gestite", layout = FinesView.class)
//@Service
public class GestiteView extends VerticalLayout {
    // @Autowired
    private final FineRepository fineRepository;
    Grid<Fine> grid = new Grid<>(Fine.class);
    TextField filterText = new TextField();

    public GestiteView(FineRepository fineRep) {
        fineRepository=fineRep;
        addClassName("gestite-view");
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
        //grid.setItems(fineRepository.getFines());
        grid.setItems(fineRepository.findAllManagedFines(VaadinSession.getCurrent().getAttribute(User.class).getRegion()));

    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.removeAllColumns();

        grid.addColumn(fine -> fine.getId()).setHeader("Codice");
        grid.addColumn(fine -> fine.getSpeedCameraId()).setHeader("Autovelox");
        grid.addColumn(fine -> fine.getReceiverFiscalCode()).setHeader("Destinatario");
        grid.addColumn(fine -> fine.getDate()).setHeader("Data");

        grid.addColumn(fine -> fine.getUser().getUsername()).setHeader("Responsabile");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filtra per autovelox...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);


        Button updateButton = new Button("Aggiorna", event -> {


            String filter = filterText.getValue();
            if(filter.equals(""))
                grid.setItems(fineRepository.findAllManagedFines(VaadinSession.getCurrent().getAttribute(User.class).getRegion()));
            else{
                if(! StringUtils.isNumeric(filter) || Integer.parseInt(filter)<0)
                    Notification.show("L'id di un autovelox è un intero");

                else
                    grid.setItems(fineRepository.findFilterAllManagedFines(VaadinSession.getCurrent().getAttribute(User.class).getRegion(), Integer.parseInt(filter)));

            }

        });
        HorizontalLayout toolbar = new HorizontalLayout(filterText, updateButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}
