package com.example.application.views.fines;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.example.application.data.entity.Fine;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Contacts | Vaadin CRM")
public class NuoveView extends VerticalLayout {
    Grid<Fine> grid = new Grid<>(Fine.class);
    TextField filterText = new TextField();

    public NuoveView() {
        addClassName("nuove-view");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        //grid.setColumns("Codice_Multa");
        grid.addColumn(fine -> fine.Codice_Multa).setHeader("Codice");
        grid.addColumn(fine -> fine.Codice_Autovelox).setHeader("Autovelox");
        grid.addColumn(fine -> fine.link_pdf).setHeader("PDF");
        grid.addColumn(fine -> new Button("Invia")).setHeader("Invia Multa");

        //grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        //grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");*/
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filtra per autovelox...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button updateButton = new Button("Aggiorna");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, updateButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}