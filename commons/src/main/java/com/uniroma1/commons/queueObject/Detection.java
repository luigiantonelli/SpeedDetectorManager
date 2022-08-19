package com.uniroma1.commons.queueObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Detection {

    private Long id;

    private Long speedCameraId;

    private int speedValue;

    private String licensePlate;

    private String carTypes;

    private String city;

    private int roadTypes;
}
