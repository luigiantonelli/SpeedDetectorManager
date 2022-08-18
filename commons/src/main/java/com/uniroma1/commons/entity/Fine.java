package com.uniroma1.commons.entity;

//import com.example.application.data.AbstractEntity;
import com.vaadin.flow.router.RouterLink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fines")
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fine_code")
    private String fineCode;

    @ManyToOne
    private SpeedCamera speedCamera;

    @Column(name = "pdf_link")
    private RouterLink pdfLink;

    @Column(name = "points")
    private int points;

    @Column(name = "amount")
    private int amount;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "car_plate")
    private String carPlate;

}
