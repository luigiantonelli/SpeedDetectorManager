package com.example.application.views.fines;



//import com.example.application.data.entity.User;
import com.example.application.data.service.AuthService;
import com.itextpdf.html2pdf.HtmlConverter;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.*;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamRegistration;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinSession;
import it.uniroma1.commons.entity.User;

import java.io.*;


@PageTitle("Login | Pubblica amministrazione Repubblica Italiana")
public class InfoFineView extends Div {
    private Span span;
    private StringBuilder text;
    private Anchor download;
    public InfoFineView(AuthService auth){
        //TextArea text = new TextArea();
        //text.setPropertyDataSource(new TextFileProperty(new File("/a/d/r/e/s/s/file")));
        span = new Span();
        text = new StringBuilder();
        download = new Anchor();

        //MODIFICARE IL TEST IN BASE ALLE PROPRIE ESIGENZE
        text.append( "<p>This set of panels asks you a couple dozen questions. From those questions AcmeCoach will build a complete document.</p>\n" );
        text.append( "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc volutpat commodo ipsum sit amet sollicitudin. Ut arcu leo, pulvinar vitae porta eu, porta sed nisi. Nulla facilisi. Aenean suscipit, nulla non vehicula aliquet, sem leo tristique erat, eget mattis arcu sem a purus. Donec at libero erat.</p>\n" );
        text.append( "<p>Morbi dolor turpis, faucibus ac pharetra quis, vulputate quis justo. Vivamus non pulvinar nisl. Mauris vitae libero et dui eleifend viverra sit amet sit amet tortor. Cras sollicitudin tincidunt dictum. Vivamus eros justo, laoreet sed commodo quis, ullamcorper nec libero.</p>\n" );
        //MODIFICARE IL TESTO IN BASE ALLE PROPRIE ESIGENZE
        span.getElement().setProperty("innerHTML",text.toString());

        Button downloadButton = new Button("Scarica PDF", event -> {
            try{
                downloadPDF(text.toString());
            }
            catch(FileNotFoundException e){
                System.out.println(e);
            }
            catch(IOException e){
                System.out.println(e);
            }
        });
        VerticalLayout output = new VerticalLayout(
                span,
                downloadButton
        );
        add(output);

    }
    private void downloadPDF(String HTML) throws FileNotFoundException, IOException {
        HtmlConverter.convertToPdf(HTML, new FileOutputStream("string-to-pdf.pdf"));
        String resultFileName = "string-to-pdf.pdf";
        System.out.println( "PDF Created!" );
        File file = new File(resultFileName);
        final StreamResource resource = getStreamResource(resultFileName, file);
        final StreamRegistration registration = VaadinSession.getCurrent().getResourceRegistry().registerResource(resource);
        UI.getCurrent().getPage().setLocation(registration.getResourceUri());
    }
    public static StreamResource getStreamResource(String fileName, File file) {
        return new StreamResource(fileName, () -> {
            try {
                return new FileInputStream(file);
            } catch (IOException e) {
                System.out.println("Error in getStreamResource: ");
                e.printStackTrace();
            }
            return null;
        });
    }
}