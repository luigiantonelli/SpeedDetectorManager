package com.example.application.views.fines;

//import com.example.application.data.entity.Fine;
//import com.example.application.data.entity.User;
import com.example.application.data.service.AuthService;
//import com.example.application.data.service.FineRepository;
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
public class NuoveView extends VerticalLayout {
    // @Autowired
    private final FineRepository fineRepository;
    Grid<Fine> grid = new Grid<>(Fine.class);
    TextField filterText = new TextField();

    public NuoveView(FineRepository fineRep) {
        fineRepository=fineRep;

        addClassName("gestite-view");
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
        //grid.setItems(fineRepository.getFines());
        //grid.setItems(fineRepository.findAllNewFines(VaadinSession.getCurrent().getAttribute(User.class).getRegion()));
        this.refreshGrid();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.removeAllColumns();

        grid.addColumn(fine -> fine.getId()).setHeader("Codice");
        grid.addColumn(fine -> fine.getSpeedCameraId()).setHeader("Autovelox");
        grid.addColumn(fine -> fine.getReceiverFiscalCode()).setHeader("Destinatario");
        grid.addColumn(fine -> fine.getDate()).setHeader("Data");

        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, fine) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> this.removeFine(fine));
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Elimina");

        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, fine) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_TERTIARY,
                            ButtonVariant.LUMO_SUCCESS
                            );
                    button.addClickListener(e -> this.manageFine(fine));
                    button.setIcon(new Icon(VaadinIcon.CHECK));
                })).setHeader("Conferma");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void removeFine(Fine fine) {
        fineRepository.delete(fine);
        refreshGrid();

    }

    private void manageFine(Fine fine){
        fine.setUser(VaadinSession.getCurrent().getAttribute(User.class));
        fineRepository.save(fine);
        refreshGrid();
    }
    public void refreshGrid(String filter){
        if(filter.equals(""))
            grid.setItems(fineRepository.findAllNewFines(VaadinSession.getCurrent().getAttribute(User.class).getRegion()));
        else{
            if(! StringUtils.isNumeric(filter) || Integer.parseInt(filter)<0)
                Notification.show("L'id di un autovelox è un intero positivo");

            else
                grid.setItems(fineRepository.findFilterAllNewFines(VaadinSession.getCurrent().getAttribute(User.class).getRegion(), Integer.parseInt(filter)));

        }
    }
    public void refreshGrid(){
        refreshGrid(filterText.getValue());
    }
    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filtra per autovelox...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);


        Button updateButton = new Button("Aggiorna", event -> {
            //grid.setItems(fineRepository.getFines().stream().toList());
            refreshGrid();
            /*String filter = filterText.getValue();
            if(filter.equals(""))
                grid.setItems(fineRepository.findAllNewFines(VaadinSession.getCurrent().getAttribute(User.class).getRegion()));
            else{
                if(! StringUtils.isNumeric(filter) || Integer.parseInt(filter)<0)
                    Notification.show("L'id di un autovelox è un intero");

                else
                    grid.setItems(fineRepository.findFilterAllNewFines(VaadinSession.getCurrent().getAttribute(User.class).getRegion(), Integer.parseInt(filter)));

            }*/

        });
        HorizontalLayout toolbar = new HorizontalLayout(filterText, updateButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}
