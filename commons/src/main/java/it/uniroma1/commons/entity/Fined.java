package it.uniroma1.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fined")
public class Fined {
    @Id
    private String code;

    @OneToMany(mappedBy = "receiver")
    private List<Fine> fines;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

}