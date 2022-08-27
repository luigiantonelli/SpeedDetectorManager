package it.uniroma1.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "speed_camera_id", referencedColumnName ="id")
    private SpeedCamera speedCamera;

    @ManyToOne
    @JoinColumn(name = "manager_code", referencedColumnName ="username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "code_receiver", referencedColumnName ="fiscalCode")
    private Person receiver;

    @ManyToOne
    @JoinColumn(name = "license_plate", referencedColumnName ="licensePlate")
    private Car car;

    @Column(name = "points")
    private int points;

    @Column(name = "amount")
    private int amount;

    @Column(name = "date")
    private LocalDateTime date;

    public Long getSpeedCameraId(){
        return speedCamera.getId();
    }
    public String getSpeedCameraRegion(){
        return speedCamera.getRegion();
    }

    public String getReceiverFiscalCode() {
        return receiver.getFiscalCode();
    }
}
