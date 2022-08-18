package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity
@Table(name = "detections")
public class Detection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "speed_value")
    private int speedValue;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "car_types")
    private String carTypes;

    @Column
    private String city;

    @Column
    private String roadTypes;
}
