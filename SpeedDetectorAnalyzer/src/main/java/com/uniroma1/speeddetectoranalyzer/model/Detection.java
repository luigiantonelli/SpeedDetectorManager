package com.uniroma1.speeddetectoranalyzer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor

//@Entity
//@Table(name = "detections")
public class Detection {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Column
    private int speedValue;

//    @Column
    private String licensePlate;

//    @Column
    private String carTypes;

//    @Column
    private String city;

//    @Column
    private String roadTypes;
}
