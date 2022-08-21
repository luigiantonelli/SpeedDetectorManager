package it.uniroma1.commons.entity;

import com.vaadin.flow.router.RouterLink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fines")
public class Fine {
    @Id
    private String fineCode;

    @ManyToOne
    @JoinColumn(name = "speed_camera_id", referencedColumnName ="id")
    private SpeedCamera speedCamera;

    @ManyToOne
    @JoinColumn(name = "manager_code", referencedColumnName ="username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "code_receiver", referencedColumnName ="code")
    private Fined receiver;

    @Column(name = "pdf_link")
    private RouterLink pdfLink;

    @Column(name = "points")
    private int points;

    @Column(name = "amount")
    private int amount;

    @Column(name = "car_plate")
    private String carPlate;

    @Column(name = "date")
    private Date date;

}
