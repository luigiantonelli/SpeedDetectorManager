package entity;

//import com.example.application.data.AbstractEntity;
import com.vaadin.flow.router.RouterLink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "speed_cameras")
public class SpeedCamera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fines")
    @OneToMany
    @JoinColumn(name = "speed_camera", referencedColumnName = "id")
    private List<Fine> fines;

    @Column(name = "region")
    private String region;

    @Column(name = "road_type")
    private String roadType;

    @Column(name = "points")
    private int points;

    @Column(name = "amount")
    private int amount;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "car_plate")
    private String carPlate;

}
